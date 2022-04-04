package com.stochanskyi.librariesdemo.data.repository.maps

import com.stochanskyi.librariesdemo.data.maps.CountryBoundsDataSource
import com.stochanskyi.librariesdemo.data.repository.maps.mapper.CountryBoundsMapper
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.CountryBound
import javax.inject.Inject

interface CountryBoundsRepository {
    fun getCountriesBounds(): List<CountryBound>
}

class CountryBoundsRepositoryImpl @Inject constructor(
    private val countryBoundsDataSource: CountryBoundsDataSource,
    private val countryBoundsMapper: CountryBoundsMapper
) : CountryBoundsRepository {

    override fun getCountriesBounds(): List<CountryBound> {
        val bounds = countryBoundsDataSource.getCountriesBounds()
        return countryBoundsMapper.mapCountriesBounds(bounds)
    }
}