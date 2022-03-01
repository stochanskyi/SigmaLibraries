package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.onSuccess
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.onSuccessSimple
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun loadImage(image: String, target: ImageView, params: ImageLoaderParams) {
        createBuilder(image, target, params)
            .into(target)
    }

    override suspend fun loadWithTimeMeasurement(
        image: String,
        target: ImageView,
        params: ImageLoaderParams
    ): Long {
                createBuilder(image, target, params)
                    .into(target)

        return 10
    }

    private fun createBuilder(
        image: String,
        target: ImageView,
        params: ImageLoaderParams
    ): RequestBuilder<Drawable> {
        return Glide.with(target).load(image)
            .tryNullable(params.placeholderRes) { placeholder(it) }
            .tryNullable(params.errorRes) { error(it) }
            .tryNullable(params.fallbackRes) { fallback(it) }
    }
}

fun <T, U> RequestBuilder<T>.tryNullable(
    param: U?,
    applier: RequestBuilder<T>.(U) -> RequestBuilder<T>
): RequestBuilder<T> {
    if (param == null) return this
    return applier(param)
}