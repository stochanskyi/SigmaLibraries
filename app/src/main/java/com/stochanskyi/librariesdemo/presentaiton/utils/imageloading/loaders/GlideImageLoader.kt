package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun loadImage(image: String, target: ImageView, params: ImageLoaderParams) {

        Glide.with(target).load(image)
            .tryNullable(params.placeholderRes) { placeholder(it) }
            .tryNullable(params.errorRes) { error(it) }
            .tryNullable(params.fallbackRes) { fallback(it) }
            .into(target)

    }

}

fun <T, U> RequestBuilder<T>.tryNullable(param: U?, applier: RequestBuilder<T>.(U) -> RequestBuilder<T>): RequestBuilder<T> {
    if (param == null) return this
    return applier(param)
}