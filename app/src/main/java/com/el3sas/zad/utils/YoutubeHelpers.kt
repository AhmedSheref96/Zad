package com.el3sas.zad.utils

import android.content.Context
import android.content.Intent
import java.util.Locale

fun shareVideo(
    context: Context,
    id: String,
    isPlaylist: Boolean,
) {
    val baseUrl =
        if (isPlaylist) {
            "https://www.youtube.com/playlist?list=$id"
        } else {
            "https://www.youtube.com/watch?v=$id"
        }

    val sendIntent =
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, baseUrl)
            type = "text/plain"
        }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

fun formatDuration(seconds: Float): String {
    val totalSeconds = seconds.toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds % 60
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
}
