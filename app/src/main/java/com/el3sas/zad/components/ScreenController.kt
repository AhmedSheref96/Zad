package com.el3sas.zad.components

import android.app.Activity
import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.el3sas.zad.R
import com.el3sas.zad.utils.enterFullscreen
import com.el3sas.zad.utils.exitFullscreen
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

@Composable
fun ScreenController(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    player: YouTubePlayer,
    playerState: PlayerConstants.PlayerState,
    isFullscreen: Boolean,
    isFullScreen: (Boolean) -> Unit = {},
) {
    IconButton(modifier = modifier, onClick = {
        isFullScreen(!isFullscreen)
        if (isFullscreen) {
            (context as Activity).enterFullscreen()
        } else {
            (context as Activity).exitFullscreen()
        }
    }) {
        Icon(
            painterResource(if (isFullscreen) R.drawable.ic_normalscreen else R.drawable.ic_fullscreen),
            "Fullscreen",
            tint = Color.White,
        )
    }
}
