package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallUseCase
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseBindsModule {

    @Binds
    fun provideSimpleCallUseCase(sampleCallUseCase: SimpleCallUseCaseImpl): SimpleCallUseCase

}