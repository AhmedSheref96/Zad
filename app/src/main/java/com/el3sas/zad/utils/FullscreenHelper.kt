package com.el3sas.zad.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View

fun Activity.enterFullscreen() {
    val decorView = window.decorView
    requestedOrientation =
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    )
}

fun Activity.exitFullscreen() {
    val decorView = window.decorView
    requestedOrientation =
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}
