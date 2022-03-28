package com.stochanskyi.librariesdemo.domain.features.location.mapper

import android.location.Location
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData

interface LocationMapper {
    fun map(location: Location): LocationData
}

class LocationMapperImpl : LocationMapper {

    override fun map(location: Location) = with(location) {
        LocationData(
            lat = latitude.toFloat(),
            long = longitude.toFloat()
        )
    }

}