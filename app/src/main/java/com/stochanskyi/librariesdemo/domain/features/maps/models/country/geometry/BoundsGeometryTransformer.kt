package com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry

interface BoundsGeometryTransformer<T> {
    fun transformPolygon(geometry: Polygon): T
    fun transformMultiPolygon(geometry: MultiPolygon): T
}