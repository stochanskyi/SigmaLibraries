package com.stochanskyi.librariesdemo.presentaiton.utils

import android.app.PendingIntent
import android.os.Build

fun intentMutableFlag(): Int {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return 0
    return PendingIntent.FLAG_MUTABLE
}

fun intentImmutableFlag(): Int {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return 0
    return PendingIntent.FLAG_IMMUTABLE
}