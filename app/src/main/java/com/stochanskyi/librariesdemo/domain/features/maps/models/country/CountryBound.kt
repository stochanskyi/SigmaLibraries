package com.stochanskyi.librariesdemo.domain.features.maps.models.country

import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.BoundsGeometry

class CountryBound(
    val countryCode: String,
    val geometry: BoundsGeometry
)