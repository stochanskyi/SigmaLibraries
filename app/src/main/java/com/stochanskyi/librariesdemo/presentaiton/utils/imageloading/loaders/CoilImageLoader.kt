package com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders

import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.stochanskyi.librariesdemo.ext.resume
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.onSuccess
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.params.ImageLoaderParams
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.system.measureTimeMillis


class CoilImageLoader @Inject constructor() : ImageLoader {

    override fun loadImage(image: String, target: ImageView, params: ImageLoaderParams) {
        target.load(image) {
            params.placeholderRes?.let { placeholder(it) }
            params.fallbackRes?.let { fallback(it) }
            params.errorRes?.let { error(it) }
        }
    }

    override suspend fun loadWithTimeMeasurement(
        image: String,
        target: ImageView,
        params: ImageLoaderParams
    ): Long {
        return measureTimeMillis {
            suspendCancellableCoroutine { continuation ->
                val request = createBuilder(image, target, params)
                    .onSuccess {
                        if (continuation.isActive) {
                            continuation.resume()
                        }
                    }
                    .build()
                target.context.imageLoader.enqueue(request)
            }
        }
    }

    private fun createBuilder(
        image: String,
        target: ImageView,
        params: ImageLoaderParams
    ): ImageRequest.Builder {
        return ImageRequest.Builder(target.context!!)
            .tryNullable(params.placeholderRes) { placeholder(it) }
            .tryNullable(params.fallbackRes) { fallback(it) }
            .tryNullable(params.errorRes) { error(it) }
            .target(target)
            .data(image)
    }

    fun <U> ImageRequest.Builder.tryNullable(
        param: U?,
        applier: ImageRequest.Builder.(U) -> ImageRequest.Builder
    ): ImageRequest.Builder {
        if (param == null) return this
        return applier(param)
    }
}