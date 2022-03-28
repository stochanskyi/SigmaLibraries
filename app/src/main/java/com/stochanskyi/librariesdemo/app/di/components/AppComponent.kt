package com.stochanskyi.librariesdemo.app.di.components

import com.stochanskyi.librariesdemo.app.di.dependencies.AppDependencies
import com.stochanskyi.librariesdemo.app.di.modules.AppModule
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityUpdateReceiver
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.ActivityRecognitionFragment
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.ActivityUpdateFragment
import com.stochanskyi.librariesdemo.presentaiton.imageloading.ImageLoadersFragment
import com.stochanskyi.librariesdemo.presentaiton.imageloading.item.ImageLoadingTestFragment
import com.stochanskyi.librariesdemo.presentaiton.locationupdate.LocationUpdateFragment
import com.stochanskyi.librariesdemo.presentaiton.simplecall.SimpleCallFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class], dependencies = [AppDependencies::class])
@Singleton
interface AppComponent {

    fun inject(fragment: SimpleCallFragment)
    fun inject(fragment: ImageLoadersFragment)
    fun inject(fragment: ImageLoadingTestFragment)
    fun inject(fragment: ActivityRecognitionFragment)
    fun inject(fragment: ActivityUpdateFragment)
    fun inject(fragment: LocationUpdateFragment)

    fun inject(receiver: ActivityUpdateReceiver)

    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }
}