package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSource
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSourceImpl
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityUpdateConsumer
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobExecutor
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobExecutorImpl
import dagger.Binds
import dagger.Module

@Module
interface DataBindsModule {

    @Binds
    fun bindJobExecutor(jobExecutor: JobExecutorImpl): JobExecutor

    @Binds
    fun bindActivityRecognitionDataSource(source: ActivityRecognitionDataSourceImpl): ActivityRecognitionDataSource

    @Binds
    fun bindActivityUpdateConsumer(source: ActivityRecognitionDataSourceImpl): ActivityUpdateConsumer

}