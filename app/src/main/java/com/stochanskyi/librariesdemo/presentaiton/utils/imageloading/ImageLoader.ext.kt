package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.request.ImageRequest
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

    @DrawableRes
    val placeholderRes: Int? = null

    @DrawableRes
    val fallbackRes: Int? = null

    @DrawableRes
    val errorRes: Int? = null

    fun build() = ImageLoaderParams(
        placeholderRes = placeholderRes,
        fallbackRes = fallbackRes,
        errorRes = errorRes
    )

}

inline fun <T> RequestBuilder<T>.onSuccess(
    crossinline block: (
        resource: T,
        model: Any?,
        target: Target<T>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ) -> Boolean
): RequestBuilder<T> {
    return addListener(object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<T>?,
            isFirstResource: Boolean
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onResourceReady(
            resource: T,
            model: Any?,
            target: Target<T>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return block(resource, model, target, dataSource, isFirstResource)
        }

    })
}

inline fun <T> RequestBuilder<T>.onSuccessSimple(crossinline block: () -> Unit): RequestBuilder<T> {
    return onSuccess { _, _, _, _, _ ->
        block()
        false
    }
}

inline fun ImageRequest.Builder.onSuccess(crossinline block: () -> Unit): ImageRequest.Builder {
    return listener(
        onSuccess = { _, _ -> block() }
    )
}