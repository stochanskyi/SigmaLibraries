package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders

import android.content.Context
import android.widget.ImageView
import coil.imageLoader
import coil.load
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams

private typealias CoilLoader = coil.ImageLoader

class CoilImageLoader(
    private val coilImageLoader: CoilLoader? = null
) : ImageLoader {

    private fun resolveCoilImageLoader(context: Context): coil.ImageLoader {
        return coilImageLoader ?: context.imageLoader
    }

    override fun loadImage(image: String, target: ImageView, params: ImageLoaderParams) {
        target.load(image, resolveCoilImageLoader(target.context)) {
            params.placeholderRes?.let { placeholder(it) }
            params.fallbackRes?.let { fallback(it) }
            params.errorRes?.let { error(it) }
        }
    }

}