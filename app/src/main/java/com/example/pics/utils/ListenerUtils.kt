package com.example.pics.utils

import android.view.View

private var isClickEnabled: Boolean = true
private val enable: () -> Unit = { isClickEnabled = true }

fun View.setOnDebouncedClickListener(delayTime: Long = 300, action: () -> Unit) {
    this.setOnClickListener { v ->
        if (isClickEnabled) {
            isClickEnabled = false
            v.postDelayed(enable, delayTime)
            action.invoke()
        }
    }
}
