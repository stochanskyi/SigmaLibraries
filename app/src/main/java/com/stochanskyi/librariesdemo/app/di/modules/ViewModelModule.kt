package com.stochanskyi.librariesdemo.app.di.modules

import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.app.di.annotations.ViewModelKey
import com.stochanskyi.librariesdemo.domain.features.maps.GetCountriesBoundsUseCase
import com.stochanskyi.librariesdemo.presentaiton.feature.map.MapDemoViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers

@Module
class ViewModelModule {

    @Provides
    @[IntoMap ViewModelKey(MapDemoViewModel::class)]
    fun provideMapDemoViewModel(getCountriesBoundsUseCase: GetCountriesBoundsUseCase): ViewModel {
        return MapDemoViewModel(
            getCountriesBoundsUseCase,
            Dispatchers.IO
        )
    }

}