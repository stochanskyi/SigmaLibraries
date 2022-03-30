package com.stochanskyi.librariesdemo.domain.features.location.mapper

import android.location.Location
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import javax.inject.Inject

interface LocationMapper {
    fun map(location: Location): LocationData
}

class LocationMapperImpl @Inject constructor() : LocationMapper {

    override fun map(location: Location) = with(location) {
        LocationData(
            lat = latitude.toFloat(),
            long = longitude.toFloat()
        )
    }

}