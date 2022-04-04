package com.stochanskyi.librariesdemo.data.maps.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CountriesBoundsDto(
    @Json(name = "features")
    val features: List<CountryBoundDto>
) {

    companion object {
        val EMPTY: CountriesBoundsDto
            get() = CountriesBoundsDto(emptyList())
    }

    @JsonClass(generateAdapter = true)
    class CountryBoundDto(
        @Json(name = "properties")
        val properties: BoundPropertiesDto,
        @Json(name = "geometry")
        val geometry: BoundGeometryDto
    )
}