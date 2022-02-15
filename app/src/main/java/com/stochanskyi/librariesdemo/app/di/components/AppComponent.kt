package com.stochanskyi.librariesdemo.app.di.components

import com.stochanskyi.librariesdemo.app.di.modules.AppModule
import com.stochanskyi.librariesdemo.presentaiton.imageloading.ImageLoadersFragment
import com.stochanskyi.librariesdemo.presentaiton.imageloading.item.ImageLoadingTestFragment
import com.stochanskyi.librariesdemo.presentaiton.simplecall.SimpleCallFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SimpleCallFragment)
    fun inject(fragment: ImageLoadersFragment)
    fun inject(fragment: ImageLoadingTestFragment)

}