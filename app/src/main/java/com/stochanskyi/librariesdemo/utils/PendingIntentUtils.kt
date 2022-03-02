package com.stochanskyi.librariesdemo.utils

import android.app.PendingIntent
import android.os.Build

fun intentMutableFlag(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.FLAG_MUTABLE
    } else {
        0
    }
}

fun intentImmutableFlag(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.FLAG_IMMUTABLE
    } else {
        0
    }
}