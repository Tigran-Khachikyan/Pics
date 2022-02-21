package com.example.pics.utils

import android.app.Activity
import android.graphics.Insets
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets

/** in pixels**/
fun Activity.getScreenWidth(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        with(DisplayMetrics()) {
            windowManager.defaultDisplay.getMetrics(this)
            widthPixels
        }
    }
}

fun Activity.getScreenHeightWithoutStatusBar(): Int {
    return getScreenHeight() - getStatusBarHeight()
}

/** in pixels**/
private fun Activity.getScreenHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.height() - insets.top - insets.bottom
    } else {
        with(DisplayMetrics()) {
            windowManager.defaultDisplay.getMetrics(this)
            heightPixels
        }
    }
}

private fun Activity.getStatusBarHeight(): Int {
    val rectangle = Rect()
    window.decorView.getWindowVisibleDisplayFrame(rectangle)
    return rectangle.top
}
