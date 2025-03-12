package com.el3sas.zad.components

import android.app.Activity
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.el3sas.zad.utils.enterFullscreen
import com.el3sas.zad.utils.exitFullscreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun YoutubePlayListView(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    playlistId: String,
    onReady: (YouTubePlayer) -> Unit = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var youtubePlayer by remember { mutableStateOf<YouTubePlayer?>(null) }
    var playerState by remember { mutableStateOf(PlayerConstants.PlayerState.UNKNOWN) }
    var isFullscreen by rememberSaveable { mutableStateOf(false) }
    var videoCurrentSecond by rememberSaveable { mutableFloatStateOf(0f) }
    var videoDuration by rememberSaveable { mutableFloatStateOf(0f) }
    var showVideoControllers by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var videoControllersVisibilityJob: Job? = null

    DisposableEffect(isFullscreen) {
        val activity = context as Activity
        val originalOrientation = activity.requestedOrientation
        if (isFullscreen) {
            context.enterFullscreen()
        } else {
            context.exitFullscreen()
        }

        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }

    ConstraintLayout(modifier = modifier.fillMaxWidth().background(Color.Black)) {
        val (youtubePlayerView, screenController, playingController, videoSeeker, transparentBg) = createRefs()
        AndroidView(
            factory = { ctx ->
                YouTubePlayerView(context).apply {
                    enableAutomaticInitialization = false
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
                                onReady(player)
                                youtubePlayer = player
//                                player.seekTo(time = videoLastSavedSecond)
                            }

                            override fun onStateChange(
                                youTubePlayer: YouTubePlayer,
                                state: PlayerConstants.PlayerState,
                            ) {
                                super.onStateChange(youTubePlayer, state)
                                playerState = state
                            }

                            override fun onCurrentSecond(
                                youTubePlayer: YouTubePlayer,
                                second: Float,
                            ) {
                                super.onCurrentSecond(youTubePlayer, second)
                                videoCurrentSecond = second
                            }

                            override fun onVideoDuration(
                                youTubePlayer: YouTubePlayer,
                                duration: Float,
                            ) {
                                super.onVideoDuration(youTubePlayer, duration)
                                videoDuration = duration
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
                    ).constrainAs(youtubePlayerView) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
        )
        if (youtubePlayer != null) {
            AnimatedVisibility(
                modifier =
                    Modifier
                        .constrainAs(screenController) {
                            top.linkTo(youtubePlayerView.top)
                            bottom.linkTo(youtubePlayerView.bottom)
                            end.linkTo(youtubePlayerView.end)
                        },
                visible = showVideoControllers,
            ) {
                ScreenController(
                    modifier =
                        Modifier
                            .size(36.dp)
                            .background(color = Color.Black.copy(alpha = .2f), shape = CircleShape)
                            .padding(8.dp),
                    player = youtubePlayer!!,
                    playerState = playerState,
                    isFullscreen = isFullscreen,
                    isFullScreen = { isFullscreen = it },
                )
            }
            AnimatedVisibility(
                modifier =
                    Modifier
                        .constrainAs(playingController) {
                            bottom.linkTo(youtubePlayerView.bottom)
                            start.linkTo(youtubePlayerView.start)
                            end.linkTo(youtubePlayerView.end)
                        },
                visible = showVideoControllers,
            ) {
                YoutubeVideoPlayingControllers(
                    player = youtubePlayer!!,
                    playerState = playerState,
                    isFullscreen = isFullscreen,
                    isFullScreen = { isFullscreen = it },
                )
            }
            AnimatedVisibility(
                modifier =
                    Modifier
                        .constrainAs(videoSeeker) {
                            bottom.linkTo(playingController.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
                visible = showVideoControllers,
            ) {
                YoutubeVideoSeeker(
                    modifier =
                        Modifier
                            .height(2.dp)
                            .fillMaxWidth(),
                    player = youtubePlayer!!,
                    playerState = playerState,
                    currentSecond = videoCurrentSecond,
                    duration = videoDuration,
                )
            }
            AnimatedVisibility(
                modifier =
                    Modifier
                        .constrainAs(transparentBg) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(videoSeeker.top, margin = 4.dp)
                        },
                visible = showVideoControllers,
            ) {
                Spacer(
                    modifier =
                        Modifier.linearGradientBackground(),
                )
            }
        }
    }

    LaunchedEffect(isFullscreen) {
        youtubePlayer?.seekTo(videoCurrentSecond)
        youtubePlayer?.play()
    }

    LaunchedEffect(playerState) {
        videoControllersVisibilityJob?.cancel()
        videoControllersVisibilityJob =
            scope.launch {
                if (playerState == PlayerConstants.PlayerState.PAUSED) {
                    showVideoControllers = true
                } else {
                    delay(400)
                    showVideoControllers = false
                }
            }
    }
}

@Preview(name = "YoutubePlayListView")
@Composable
private fun PreviewYoutubePlayListView() {
    YoutubePlayListView(
        playlistId = "PLzufeTFnhupz8nXyghqkI-Ur2T7ZR7kzE",
    )
}
