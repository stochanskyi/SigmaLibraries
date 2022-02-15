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

suspend fun ImageView.loadImageAndMeasure(
    image: String,
    imageLoader: ImageLoader,
    paramsBuilder: ImageLoaderParamsBuilder.() -> Unit
): Long {
    val builder = ImageLoaderParamsBuilder()
    paramsBuilder(builder)
    val params = builder.build()

    return imageLoader.loadWithTimeMeasurement(image, this, params)
}

class ImageLoaderParamsBuilder {

    @DrawableRes
    var placeholderRes: Int? = null

    @DrawableRes
    var fallbackRes: Int? = null

    @DrawableRes
    var errorRes: Int? = null

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
            e?.logRootCauses("Glide Failure")
            return false
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