package com.stochanskyi.librariesdemo.domain.features.simplecall

interface SimpleCallRepository {

    suspend fun getSimpleStringWithDelay(delayDuration: Long): String

}