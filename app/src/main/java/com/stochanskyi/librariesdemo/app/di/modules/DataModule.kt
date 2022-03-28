package com.stochanskyi.librariesdemo.app.di.modules

import android.content.Context
import com.google.android.gms.location.*
import com.stochanskyi.librariesdemo.data.location.LocationDataSource
import com.stochanskyi.librariesdemo.data.location.LocationDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideActivityRecognitionClient(context: Context): ActivityRecognitionClient {
        return ActivityRecognition.getClient(context)
    }

    @Provides
    fun provideLocationClient(context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideLocationDataSource(
        locationProviderClient: FusedLocationProviderClient
    ): LocationDataSource {
        return LocationDataSourceImpl(
            locationProviderClient,
            LocationRequest.PRIORITY_HIGH_ACCURACY
        )
    }
}