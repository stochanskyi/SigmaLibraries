package com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions

import androidx.annotation.StringRes
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader

class ImageLoaderDefinition(
    val id: Int,
    @StringRes val nameRes: Int,
    val imageLoader: ImageLoader
)