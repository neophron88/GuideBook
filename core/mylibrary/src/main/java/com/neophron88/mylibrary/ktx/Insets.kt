package com.neophron88.mylibrary.ktx

import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.FragmentActivity


fun View.doOnApplyWindowInsets(target: Bars) {

    setOnApplyWindowInsetsListener { v, insets ->
        val insetsCompat = WindowInsetsCompat
            .toWindowInsetsCompat(insets)
            .getInsets(target.defineTarget())

        when (target) {
            is Bars.SystemBars -> {
                val bottom = insetsCompat.bottom
                val top = insetsCompat.top
                v.updatePadding(top = top, bottom = bottom)
            }
            is Bars.StatusBars -> {
                val top = insetsCompat.top
                v.updatePadding(top = top)
            }
            is Bars.NavigationBars -> {
                val bottom = insetsCompat.bottom
                v.updatePadding(bottom = bottom)
            }
        }

        insets
    }

    requestApplyInsets()
}


fun FragmentActivity.hideBars(bars: Bars) {

    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }

    windowInsetsController.isAppearanceLightStatusBars = true
    windowInsetsController.isAppearanceLightNavigationBars = true
    windowInsetsController.hide(bars.defineTarget())

}

fun FragmentActivity.showBars(bars: Bars) {
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.isAppearanceLightStatusBars = false
    windowInsetsController.isAppearanceLightNavigationBars = false
    windowInsetsController.show(bars.defineTarget())
}


sealed class Bars {
    object SystemBars : Bars()
    object StatusBars : Bars()
    object NavigationBars : Bars()
}

fun Bars.defineTarget(): Int =
    when (this) {
        is Bars.SystemBars -> WindowInsetsCompat.Type.systemBars()
        is Bars.StatusBars -> WindowInsetsCompat.Type.statusBars()
        is Bars.NavigationBars -> WindowInsetsCompat.Type.navigationBars()
    }


