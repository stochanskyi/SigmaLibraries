package com.stochanskyi.librariesdemo.data.network.simplecall

/**
 * Should be replaced with retrofit api
 */

interface SimpleCallApi {

    suspend fun requestString(): String

}

class SimpleCallApiImpl : SimpleCallApi {

    override suspend fun requestString(): String {
        return "Demo mocked string"
    }

}