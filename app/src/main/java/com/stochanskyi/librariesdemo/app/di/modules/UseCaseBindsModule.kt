package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCase
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCaseImpl
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.StopActivityUpdatesUseCase
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.StopActivityUpdatesUseCaseImpl
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
    fun bindStopActivityUpdatesUseCase(stopActivityUpdatesUseCase: StopActivityUpdatesUseCaseImpl): StopActivityUpdatesUseCase

}