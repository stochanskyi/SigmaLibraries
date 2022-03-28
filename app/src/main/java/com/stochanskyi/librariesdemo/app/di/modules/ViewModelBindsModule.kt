package com.stochanskyi.librariesdemo.app.di.modules

import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.app.di.annotations.ViewModelKey
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.ActivityRecognitionViewModel
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.ActivityUpdateViewModel
import com.stochanskyi.librariesdemo.presentaiton.imageloading.ImageLoadersViewModel
import com.stochanskyi.librariesdemo.presentaiton.imageloading.item.ImageLoadingTestViewModel
import com.stochanskyi.librariesdemo.presentaiton.locationupdate.LocationUpdateViewModel
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

    @Binds
    @[IntoMap ViewModelKey(ImageLoadingTestViewModel::class)]
    fun bindImageLoadingTestViewModel(imageLoadingTestViewModel: ImageLoadingTestViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ActivityRecognitionViewModel::class)]
    fun bindActivityRecognitionViewModel(activityRecognitionViewModel: ActivityRecognitionViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ActivityUpdateViewModel::class)]
    fun bindActivityUpdateViewModel(activityUpdateViewModel: ActivityUpdateViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(LocationUpdateViewModel::class)]
    fun binLocationUpdateViewModel(locationUpdateViewModel: LocationUpdateViewModel): ViewModel

}