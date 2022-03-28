package com.stochanskyi.librariesdemo.domain.features.location

import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData

interface LocationRepository {

    suspend fun getCurrentLocation(): LocationData

    suspend fun getLastLocation(): LocationData

}