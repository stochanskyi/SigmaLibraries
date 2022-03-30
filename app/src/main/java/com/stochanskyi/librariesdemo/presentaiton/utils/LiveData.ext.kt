package com.stochanskyi.librariesdemo.presentaiton.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

inline fun <T, R> LiveData<T>.map(crossinline mapper: (T) -> R): LiveData<R> {
    return Transformations.map(this) {
        mapper(it)
    }
}