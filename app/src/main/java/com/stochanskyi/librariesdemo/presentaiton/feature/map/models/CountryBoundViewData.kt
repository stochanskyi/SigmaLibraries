package com.stochanskyi.librariesdemo.presentaiton.feature.map.models

import androidx.annotation.ColorRes
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.BoundsGeometry

class CountryBoundViewData(
    @ColorRes val color: Int,
    val geometry: BoundsGeometry
)