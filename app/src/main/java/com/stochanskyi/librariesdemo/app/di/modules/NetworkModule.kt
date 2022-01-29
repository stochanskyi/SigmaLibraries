package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.data.network.simplecall.SimpleCallApi
import com.stochanskyi.librariesdemo.data.network.simplecall.SimpleCallApiImpl
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    //TODO replace with retrofit api
    @Provides
    fun provideSimpleCallApi(): SimpleCallApi = SimpleCallApiImpl()

}