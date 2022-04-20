package com.stochanskyi.librariesdemo.app.di.components

import com.stochanskyi.librariesdemo.app.di.dependencies.AppDependencies
import com.stochanskyi.librariesdemo.app.di.modules.AppModule
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityUpdateReceiver
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.ActivityRecognitionFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.ActivityUpdateFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.BiometricsDemoFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.map.MapDemoFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.imageloading.ImageLoadersFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.imageloading.item.ImageLoadingTestFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.LocationUpdateFragment
import com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs.UpdateLocationWorker
import com.stochanskyi.librariesdemo.presentaiton.feature.simplecall.SimpleCallFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class], dependencies = [AppDependencies::class])
@Singleton
interface AppComponent {

    // Fragments
    fun inject(fragment: SimpleCallFragment)
    fun inject(fragment: ImageLoadersFragment)
    fun inject(fragment: ImageLoadingTestFragment)
    fun inject(fragment: ActivityRecognitionFragment)
    fun inject(fragment: ActivityUpdateFragment)
    fun inject(fragment: LocationUpdateFragment)
    fun inject(fragment: MapDemoFragment)
    fun inject(fragment: BiometricsDemoFragment)

    // Broadcast Receivers
    fun inject(receiver: ActivityUpdateReceiver)

    // Workers
    fun inject(worker: UpdateLocationWorker)

    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder

        fun build(): AppComponent
    }
}