package com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions

import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders.CoilImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders.GlideImageLoader
import javax.inject.Inject

interface ImageLoaderDefinitions {

    fun getLoaders(): List<ImageLoaderDefinition>

    fun getLoader(id: Int): ImageLoaderDefinition?

    fun getLoaderOrDefault(id: Int): ImageLoaderDefinition

}

class ImageLoaderDefinitionsImpl @Inject constructor(
    glideImageLoader: GlideImageLoader,
    coilImageLoader: CoilImageLoader
) : ImageLoaderDefinitions {

    companion object {
        private const val GLIDE_IMAGE_LOADER_ID = 1
        private const val COIL_IMAGE_LOADER_ID = 2
    }

    private val loaders: List<ImageLoaderDefinition> = listOf(
        ImageLoaderDefinition(
            COIL_IMAGE_LOADER_ID,
            R.string.image_loader_coil,
            coilImageLoader
        ),
        ImageLoaderDefinition(
            GLIDE_IMAGE_LOADER_ID,
            R.string.image_loader_glide,
            glideImageLoader
        )
    )

    override fun getLoaders(): List<ImageLoaderDefinition> = loaders

    override fun getLoader(id: Int): ImageLoaderDefinition? {
        return loaders.firstOrNull { it.id == id }
    }

    override fun getLoaderOrDefault(id: Int): ImageLoaderDefinition {
        return getLoader(id) ?: loaders.first()
    }

}