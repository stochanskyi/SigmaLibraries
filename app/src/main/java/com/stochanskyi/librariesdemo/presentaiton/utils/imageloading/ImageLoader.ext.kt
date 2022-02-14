package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams

fun ImageView.loadImage(
    image: String,
    imageLoader: ImageLoader,
    paramsBuilder: ImageLoaderParamsBuilder.() -> Unit
) {
    val builder = ImageLoaderParamsBuilder()
    paramsBuilder(builder)
    val params = builder.build()

    imageLoader.loadImage(image, this, params)
}

class ImageLoaderParamsBuilder {

    @DrawableRes val placeholderRes: Int? = null
    @DrawableRes val fallbackRes: Int? = null
    @DrawableRes val errorRes: Int? = null

    fun build() = ImageLoaderParams(
        placeholderRes = placeholderRes,
        fallbackRes = fallbackRes,
        errorRes = errorRes
    )

}