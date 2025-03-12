package com.el3sas.zad.components

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.el3sas.zad.utils.shareVideo

@Composable
fun YoutubeShareButton(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    isPlaylist: Boolean,
    id: String,
) {
    IconButton(modifier = Modifier.size(42.dp), onClick = {
        shareVideo(
            context = context,
            id = id,
            isPlaylist = isPlaylist,
        )
    }) {
        Icon(Icons.Default.Share, "Share", tint = Color.White)
    }
}
