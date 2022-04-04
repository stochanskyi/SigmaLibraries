package com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry


interface BoundsGeometry {
    fun <T> transform(transformer: BoundsGeometryTransformer<T>): T

}

class Polygon(
    val coordinates: List<Coordinate>,
    val holes: List<List<Coordinate>>
): BoundsGeometry {

    override fun <T> transform(transformer: BoundsGeometryTransformer<T>): T {
        return transformer.transformPolygon(this)
    }
}

class MultiPolygon(
    val polygons: List<Polygon>
): BoundsGeometry {

    override fun <T> transform(transformer: BoundsGeometryTransformer<T>): T {
        return transformer.transformMultiPolygon(this)
    }
}