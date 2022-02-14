package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading

import android.widget.ImageView
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams

interface ImageLoader {

    fun loadImage(image: String, target: ImageView, params: ImageLoaderParams)

}