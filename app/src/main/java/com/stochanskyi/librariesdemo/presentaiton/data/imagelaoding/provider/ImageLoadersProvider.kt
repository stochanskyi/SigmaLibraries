package com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.provider

import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions.ImageLoaderDefinitions
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions.ImageLoaderDefinition
import javax.inject.Inject

interface ImageLoadersProvider {

    fun getLoaders(): List<ImageLoaderDefinition>

    fun getLoader(id: Int): ImageLoaderDefinition?

    fun getLoaderOrDefault(id: Int): ImageLoaderDefinition

}

class ImageLoadersProviderImpl @Inject constructor(
    private val imageLoaderDefinitions: ImageLoaderDefinitions
) : ImageLoadersProvider {

    override fun getLoaders(): List<ImageLoaderDefinition> = imageLoaderDefinitions.getLoaders()

    override fun getLoader(id: Int): ImageLoaderDefinition? = imageLoaderDefinitions.getLoader(id)

    override fun getLoaderOrDefault(id: Int): ImageLoaderDefinition =
        imageLoaderDefinitions.getLoaderOrDefault(id)

}