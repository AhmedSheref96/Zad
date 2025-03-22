package com.el3sas.zad.components

import android.app.Activity
import android.content.Context
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.el3sas.zad.utils.enterFullscreen
import com.el3sas.zad.utils.exitFullscreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
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
    val lifecycleOwner = LocalLifecycleOwner.current
    var youtubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
    var isFullscreen by rememberSaveable { mutableStateOf(false) }
    val originalOrientation = rememberSaveable { activity.requestedOrientation }

    DisposableEffect(isFullscreen) {
        if (isFullscreen) {
            activity.enterFullscreen()
        } else {
            activity.exitFullscreen()
        }

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
//                    enableBackgroundPlayback(true)
                    val tracker = YouTubePlayerTracker()

                    val options =
                        IFramePlayerOptions
                            .Builder()
                            .controls(0)
                            .listType("playlist")
                            .list(playlistId)
                            .fullscreen(1)
                            .build()

                    initialize(
                        object : AbstractYouTubePlayerListener() {
                            override fun onReady(player: YouTubePlayer) {
                                player.addListener(tracker)
                                val defaultPlayerUiController =
                                    DefaultPlayerUiController(this@apply, player)

                                defaultPlayerUiController.setFullscreenButtonClickListener {
                                    isFullscreen = !isFullscreen
                                }
                                this@apply.setCustomPlayerUi(defaultPlayerUiController.rootView)
                                onReady(player)
                                youtubePlayer = player
                                player.playVideoAt(0)
                                player.seekTo(tracker.currentSecond)
                            }
                        },
                        options,
                    )
                    lifecycleOwner.lifecycle.addObserver(this)
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
