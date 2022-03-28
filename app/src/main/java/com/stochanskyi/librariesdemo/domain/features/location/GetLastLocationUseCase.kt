package com.stochanskyi.librariesdemo.domain.features.location

import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import javax.inject.Inject

interface GetLastLocationUseCase {
    suspend operator fun invoke(): LocationData
}

class GetLastLocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : GetLastLocationUseCase {

    override suspend fun invoke(): LocationData {
        return locationRepository.getLastLocation()
    }
}