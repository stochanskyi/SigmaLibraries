package com.stochanskyi.librariesdemo.presentaiton.feature.imageloading.models

import androidx.annotation.StringRes

data class ImageLoaderViewData(
    @StringRes val nameRes: Int,
    val id: Int
)