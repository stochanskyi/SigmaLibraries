package com.stochanskyi.librariesdemo.presentaiton.utils.maps

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions

fun PolygonOptions.addHoles(
    holes: Iterable<Iterable<LatLng>>
) = apply {
    holes.forEach {
        addHole(it)
    }
}