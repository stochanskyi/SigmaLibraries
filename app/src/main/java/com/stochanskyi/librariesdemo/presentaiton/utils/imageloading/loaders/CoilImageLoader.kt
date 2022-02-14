package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders

import android.widget.ImageView
import coil.load
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams
import javax.inject.Inject


class CoilImageLoader @Inject constructor() : ImageLoader {

    override fun loadImage(image: String, target: ImageView, params: ImageLoaderParams) {
        target.load(image) {
            params.placeholderRes?.let { placeholder(it) }
            params.fallbackRes?.let { fallback(it) }
            params.errorRes?.let { error(it) }
        }
    }

}