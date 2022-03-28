package com.stochanskyi.librariesdemo.domain.features.location

import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import javax.inject.Inject

interface GetCurrentLocationUseCase {
    suspend operator fun invoke(): LocationData
}

class GetCurrentLocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : GetCurrentLocationUseCase {

    override suspend fun invoke(): LocationData {
        return locationRepository.getCurrentLocation()
    }
}