package com.stochanskyi.librariesdemo.ext

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

fun Continuation<Unit>.resume() = resume(Unit)