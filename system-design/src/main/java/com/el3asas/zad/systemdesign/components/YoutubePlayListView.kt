package com.el3asas.zad.systemdesign.components

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.el3asas.zad.systemdesign.utils.enterFullscreen
import com.el3asas.zad.systemdesign.utils.exitFullscreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayListView(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    activity: Activity = LocalActivity.current!!,
    playlistId: String,
    onReady: (YouTubePlayer) -> Unit = {},
) {
    var youtubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
    var isFullscreen by rememberSaveable { mutableStateOf(false) }
    val originalOrientation = rememberSaveable { activity.requestedOrientation }
    var lastSavedVideoLocation by rememberSaveable { mutableFloatStateOf(0f) }

    LaunchedEffect(isFullscreen) {
        if (isFullscreen) {
            activity.enterFullscreen()
        } else {
            activity.exitFullscreen()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = { ctx ->
                YouTubePlayerView(context).apply {
                    enableAutomaticInitialization = false
                    enableBackgroundPlayback(true)
                    val tracker = YouTubePlayerTracker()

                    val options =
                        IFramePlayerOptions
                            .Builder()
                            .controls(1)
                            .listType("playlist")
                            .list(playlistId)
                            .fullscreen(1)
                            .build()

                    addFullscreenListener(
                        object : FullscreenListener {
                            override fun onEnterFullscreen(
                                fullscreenView: View,
                                exitFullscreen: () -> Unit,
                            ) {
                                if (isFullscreen) {
                                    exitFullscreen.invoke()
                                } else {
                                    lastSavedVideoLocation = tracker.currentSecond
                                    isFullscreen = true
                                }
                            }

                            override fun onExitFullscreen() {
                                lastSavedVideoLocation = tracker.currentSecond
                                isFullscreen = false
                            }
                        },
                    )

                    initialize(
                        object : AbstractYouTubePlayerListener() {
                            override fun onReady(player: YouTubePlayer) {
                                player.addListener(tracker)
                                onReady(player)
                                youtubePlayer = player
                                player.seekTo(lastSavedVideoLocation)
                                player.play()
                            }
                        },
                        true,
                        options,
                    )
                }
            },
            modifier =
                modifier
                    .then(
                        if (isFullscreen) {
                            Modifier
                                .fillMaxHeight()
                                .aspectRatio(16f / 9f)
                        } else {
                            Modifier.fillMaxWidth()
                        },
                    ),
        )
    }
}

@Preview(name = "YoutubePlayListView")
@Composable
private fun PreviewYoutubePlayListView() {
    YoutubePlayListView(
        playlistId = "PLzufeTFnhupz8nXyghqkI-Ur2T7ZR7kzE",
    )
}
