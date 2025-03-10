package com.el3sas.zad.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayListView(
    modifier: Modifier = Modifier,
    playlistId: String,
    onReady: (YouTubePlayer) -> Unit = {},
) {
    AndroidView(
        factory = { ctx ->
            YouTubePlayerView(ctx).apply {
                enableAutomaticInitialization = false
                val iFramePlayerOptions =
                    IFramePlayerOptions
                        .Builder()
                        .controls(1)
                        .listType("playlist")
                        .list(playlistId)
                        .fullscreen(1)
                        .build()

                initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            onReady(youTubePlayer)
                        }
                    },
                    iFramePlayerOptions,
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(name = "YoutubePlayListView")
@Composable
private fun PreviewYoutubePlayListView() {
    YoutubePlayListView(
        playlistId = "",
    )
}
