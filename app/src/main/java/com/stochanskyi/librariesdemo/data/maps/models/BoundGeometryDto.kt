package com.stochanskyi.librariesdemo.data.maps.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

typealias CoordinateData = List<Double>

typealias PolygonData = List<List<CoordinateData>>

typealias MultiPolygonData = List<PolygonData>

val countryBoundsFactory: JsonAdapter.Factory = PolymorphicJsonAdapterFactory
    .of(BoundGeometryDto::class.java, BoundGeometryDto.TYPE_KEY)
    .withSubtype(BoundGeometryDto.PolygonDto::class.java, BoundGeometryDto.PolygonDto.TYPE_NAME)
    .withSubtype(BoundGeometryDto.MultiPolygonDto::class.java, BoundGeometryDto.MultiPolygonDto.TYPE_NAME)

sealed interface BoundGeometryDto {
    companion object {
        const val TYPE_KEY = "type"
    }

    val type: String

    @JsonClass(generateAdapter = true)
    class PolygonDto(
        @Json(name = "coordinates")
        val coordinates: PolygonData
    ) : BoundGeometryDto {

        companion object {
            const val TYPE_NAME = "Polygon"
        }

        override val type: String = TYPE_NAME

    }

    @JsonClass(generateAdapter = true)
    class MultiPolygonDto(
        @Json(name = "coordinates")
        val coordinates: MultiPolygonData
    ) : BoundGeometryDto {

        companion object {
            const val TYPE_NAME = "MultiPolygon"
        }

        override val type: String = "MultiPolygon"

    }
}