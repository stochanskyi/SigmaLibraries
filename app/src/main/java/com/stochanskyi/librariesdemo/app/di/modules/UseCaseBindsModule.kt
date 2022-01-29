package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.domain.features.simplecall.SampleCallUseCase
import com.stochanskyi.librariesdemo.domain.features.simplecall.SampleCallUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseBindsModule {

    @Binds
    fun provideSimpleCallUseCase(sampleCallUseCase: SampleCallUseCaseImpl): SampleCallUseCase

}