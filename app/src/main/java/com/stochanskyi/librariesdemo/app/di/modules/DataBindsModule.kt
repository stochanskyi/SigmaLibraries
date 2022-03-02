package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSource
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSourceImpl
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityUpdateConsumer
import dagger.Binds
import dagger.Module

@Module
interface DataBindsModule {

    @Binds
    fun bindActivityRecognitionDataSource(source: ActivityRecognitionDataSourceImpl): ActivityRecognitionDataSource

    @Binds
    fun bindActivityUpdateConsumer(source: ActivityRecognitionDataSourceImpl): ActivityUpdateConsumer

}