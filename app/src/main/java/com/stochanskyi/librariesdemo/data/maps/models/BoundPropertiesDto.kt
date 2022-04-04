package com.stochanskyi.librariesdemo.data.maps.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BoundPropertiesDto(
    @Json(name = "iso_a3")
    val countryCode: String
)