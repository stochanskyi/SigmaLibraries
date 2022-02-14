package com.stochanskyi.librariesdemo.ext

fun <T> MutableCollection<T>.clearAndAdd(collection: Collection<T>) {
    clear()
    addAll(collection)
}