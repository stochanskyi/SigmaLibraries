package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCase
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCaseImpl
import com.stochanskyi.librariesdemo.domain.features.location.GetCurrentLocationUseCase
import com.stochanskyi.librariesdemo.domain.features.location.GetCurrentLocationUseCaseImpl
import com.stochanskyi.librariesdemo.domain.features.location.GetLastLocationUseCase
import com.stochanskyi.librariesdemo.domain.features.location.GetLastLocationUseCaseImpl
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallUseCase
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseBindsModule {

    @Binds
    fun bindSimpleCallUseCase(sampleCallUseCase: SimpleCallUseCaseImpl): SimpleCallUseCase

    @Binds
    fun bindObserveActivityUpdateUseCase(observeActivityUpdateUseCase: ObserveActivityUpdateUseCaseImpl): ObserveActivityUpdateUseCase

    @Binds
    fun bindGetCurrentLocationUseCase(getCurrentLocationUseCase: GetCurrentLocationUseCaseImpl): GetCurrentLocationUseCase

    @Binds
    fun bindGetLastLocationUseCase(getLastLocationUseCase: GetLastLocationUseCaseImpl): GetLastLocationUseCase
}