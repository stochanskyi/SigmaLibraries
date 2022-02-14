package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions.ImageLoaderDefinitions
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions.ImageLoaderDefinitionsImpl
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.provider.ImageLoadersProvider
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.provider.ImageLoadersProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface ImageLoadersBindingModule {

    @Binds
    fun bindImageLoadersProvider(imageLoadersProvider: ImageLoadersProviderImpl): ImageLoadersProvider

    @Binds
    fun bindImageLoaderDefinitions(imageLoaderDefinitions: ImageLoaderDefinitionsImpl): ImageLoaderDefinitions

//    @Binds
//    fun bindCoilLoader(coilImageLoader: CoilImageLoader): ImageLoader
//
//    @Binds
//    fun bindGlideLoader(glideImageLoader: GlideImageLoader): ImageLoader

}