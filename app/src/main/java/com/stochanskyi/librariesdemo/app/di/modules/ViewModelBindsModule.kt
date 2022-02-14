package com.stochanskyi.librariesdemo.app.di.modules

import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.app.di.annotations.ViewModelKey
import com.stochanskyi.librariesdemo.presentaiton.imageloading.ImageLoadersViewModel
import com.stochanskyi.librariesdemo.presentaiton.simplecall.SimpleCallViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelBindsModule {

    @Binds
    @[IntoMap ViewModelKey(SimpleCallViewModel::class)]
    fun bindSimpleCallViewModel(simpleCallViewModel: SimpleCallViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ImageLoadersViewModel::class)]
    fun bindImageLoadersViewModel(imageLoadersViewModel: ImageLoadersViewModel): ViewModel
}