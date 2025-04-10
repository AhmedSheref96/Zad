package com.el3asas.zad.systemdesign.utils

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import timber.log.Timber

fun openYoutubeLoginWebView(
    context: Context,
    onLoggedIn: (Boolean) -> Unit,
): WebView {
    val webView = WebView(context)
    webView.settings.javaScriptEnabled = true
    webView.settings.domStorageEnabled = true
    webView.settings.savePassword = true
    webView.settings.saveFormData = true
    webView.loadUrl(
        "https://accounts.google.com/ServiceLogin?service=youtube&uilel=3&passive=true&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Dm%26hl%3Dtr%26next%3Dhttps%253A%252F%252Fm.youtube.com%252F",
    )
    webView.webViewClient =
        object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest,
            ): Boolean {
                var isLogged = false
                if (
                    request.url.toString().startsWith("https://m.youtube.com") ||
                    request.url.toString().startsWith("https://www.youtube.com")
                ) {
                    Timber.d("Logged in")
                    Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show()
                    isLogged = true
                }
                onLoggedIn(isLogged)
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    return webView
}
