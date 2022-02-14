package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params

import androidx.annotation.DrawableRes

class ImageLoaderParams(
    @DrawableRes val placeholderRes: Int?,
    @DrawableRes val fallbackRes: Int?,
    @DrawableRes val errorRes: Int?,
)