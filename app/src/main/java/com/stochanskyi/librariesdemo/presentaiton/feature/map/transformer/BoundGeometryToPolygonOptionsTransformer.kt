package com.stochanskyi.librariesdemo.presentaiton.feature.map.transformer

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.BoundsGeometryTransformer
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.Coordinate
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.MultiPolygon
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.Polygon
import com.stochanskyi.librariesdemo.presentaiton.utils.maps.addHoles

class BoundGeometryToPolygonOptionsTransformer : BoundsGeometryTransformer<List<PolygonOptions>> {

    override fun transformPolygon(geometry: Polygon): List<PolygonOptions> {
        return listOf(transformSinglePolygon(geometry))
    }

    override fun transformMultiPolygon(geometry: MultiPolygon): List<PolygonOptions> {
        return geometry.polygons.map { transformSinglePolygon(it) }
    }

    private fun transformSinglePolygon(geometry: Polygon): PolygonOptions {
        return PolygonOptions()
            .addAll(geometry.coordinates.parse())
            .addHoles(geometry.holes.map { it.parse() })
    }

    private fun List<Coordinate>.parse(): List<LatLng> {
        return map { it.parse() }
    }

    private fun Coordinate.parse(): LatLng {
        return LatLng(lat, long)
    }

}