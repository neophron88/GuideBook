package com.neophron88.upcoming.presentation.helper

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient


inline fun createWebViewClient(
    crossinline onPageStarted: () -> Unit,
    crossinline onPageFinished: () -> Unit
) =
    object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            onPageStarted()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            onPageFinished()
        }
    }
