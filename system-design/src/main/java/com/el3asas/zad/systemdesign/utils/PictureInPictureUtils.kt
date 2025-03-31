package com.el3asas.zad.systemdesign.utils

import android.app.PictureInPictureParams
import android.os.Build
import android.util.Rational
import androidx.activity.ComponentActivity

fun enterPipMode(activity: ComponentActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val params =
            PictureInPictureParams
                .Builder()
                .setAspectRatio(Rational(16, 9))
                .build()
        activity.enterPictureInPictureMode(params)
    }
}
