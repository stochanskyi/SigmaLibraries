package com.stochanskyi.librariesdemo.app.di.modules

import com.stochanskyi.librariesdemo.data.repository.activityrecognition.ActivityRecognitionRepositoryImpl
import com.stochanskyi.librariesdemo.data.repository.location.LocationRepositoryImpl
import com.stochanskyi.librariesdemo.data.repository.simplecall.SimpleCallRepositoryImpl
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ActivityRecognitionRepository
import com.stochanskyi.librariesdemo.domain.features.location.LocationRepository
import com.stochanskyi.librariesdemo.domain.features.location.mapper.LocationMapper
import com.stochanskyi.librariesdemo.domain.features.location.mapper.LocationMapperImpl
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryBindsModule {

    @Binds
    fun bindSimpleCallRepository(simpleCallRepository: SimpleCallRepositoryImpl): SimpleCallRepository

    @Binds
    fun bindActivityRecognitionRepository(activityRecognitionRepository: ActivityRecognitionRepositoryImpl): ActivityRecognitionRepository

    @Binds
    fun bindLocationRepository(locationRepository: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindLocationMapper(locationMapper: LocationMapperImpl): LocationMapper

}