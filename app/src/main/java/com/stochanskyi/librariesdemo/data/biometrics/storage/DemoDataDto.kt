package com.stochanskyi.librariesdemo.data.biometrics.storage

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DemoDataDto(
    val data: ByteArray,
    val initVector: ByteArray
)