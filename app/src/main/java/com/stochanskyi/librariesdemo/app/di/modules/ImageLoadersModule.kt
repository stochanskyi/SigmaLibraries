package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders.CoilImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loaders.GlideImageLoader
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Qualifier

@Module
interface ImageLoadersBindingModule {

    @Binds
    @[IntoMap StringKey("coil_loader")]
    fun bindCoilLoader(coilImageLoader: CoilImageLoader): ImageLoader

    @Binds
    @[IntoMap StringKey("glide_loader")]
    fun bindGlideLoader(glideImageLoader: GlideImageLoader): ImageLoader

}

@Module(includes = [ImageLoadersBindingModule::class])
class ImageLoadersModule {

    @ImageLoadersList
    fun provideImageLoaderTypes(map: Map<String, @JvmSuppressWildcards ImageLoader>): List<String> {
        return map.keys.toList()
    }

}


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ImageLoadersList