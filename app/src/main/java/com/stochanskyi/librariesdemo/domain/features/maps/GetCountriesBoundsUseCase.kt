package com.stochanskyi.librariesdemo.domain.features.maps

import com.stochanskyi.librariesdemo.data.repository.maps.CountryBoundsRepository
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.CountryBound
import javax.inject.Inject

interface GetCountriesBoundsUseCase {
    operator fun invoke(): List<CountryBound>
}

class GetCountriesBoundsUseCaseImpl @Inject constructor(
    private val countryBoundsRepository: CountryBoundsRepository
): GetCountriesBoundsUseCase {

    override fun invoke(): List<CountryBound> {
        return countryBoundsRepository.getCountriesBounds()
    }
}