package com.el3sas.zad.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.el3sas.zad.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

@Composable
fun YoutubeVideoPlayingControllers(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    player: YouTubePlayer,
    playerState: PlayerConstants.PlayerState,
    isFullscreen: Boolean,
    isFullScreen: (Boolean) -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(modifier = Modifier.size(42.dp), onClick = { player.previousVideo() }) {
            Icon(
                painterResource(R.drawable.ic_previous_video),
                "Previous",
                tint = Color.White,
            )
        }

        IconButton(modifier = Modifier.size(42.dp), onClick = {
            if (playerState == PlayerConstants.PlayerState.PLAYING) {
                player.pause()
            } else {
                player.play()
            }
        }) {
            Icon(
                painterResource(
                    if (playerState == PlayerConstants.PlayerState.PLAYING) {
                        R.drawable.ic_pause_video
                    } else {
                        R.drawable.ic_play_video
                    },
                ),
                "Play/Pause",
                tint = Color.White,
            )
        }

        IconButton(modifier = Modifier.size(42.dp), onClick = { player.nextVideo() }) {
            Icon(painterResource(R.drawable.ic_next_video), "Next", tint = Color.White)
        }
    }
}
