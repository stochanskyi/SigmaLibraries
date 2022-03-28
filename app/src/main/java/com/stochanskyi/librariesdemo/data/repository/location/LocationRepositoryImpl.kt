package com.stochanskyi.librariesdemo.data.repository.location

import com.stochanskyi.librariesdemo.data.location.LocationDataSource
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import com.stochanskyi.librariesdemo.domain.features.location.LocationRepository
import com.stochanskyi.librariesdemo.domain.features.location.mapper.LocationMapper
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val mapper: LocationMapper,
): LocationRepository {

    override suspend fun getCurrentLocation(): LocationData {
        val location = locationDataSource.getCurrentLocation()
        return mapper.map(location)
    }

    override suspend fun getLastLocation(): LocationData {
        val location = locationDataSource.getLastLocation()
        return mapper.map(location)
    }
}