package com.stochanskyi.librariesdemo.app.di.modules

import android.content.Context
import androidx.work.WorkManager
import com.google.android.gms.location.*
import com.squareup.moshi.Moshi
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.data.biometrics.*
import com.stochanskyi.librariesdemo.data.biometrics.storage.DemoDataStorage
import com.stochanskyi.librariesdemo.data.biometrics.storage.DemoDataStorageImpl
import com.stochanskyi.librariesdemo.data.location.LocationDataSource
import com.stochanskyi.librariesdemo.data.location.LocationDataSourceImpl
import com.stochanskyi.librariesdemo.data.maps.CountryBoundsDataSource
import com.stochanskyi.librariesdemo.data.maps.CountryBoundsDataSourceImpl
import com.stochanskyi.librariesdemo.data.maps.models.countryBoundsFactory
import dagger.Module
import dagger.Provides
import java.security.KeyStore
import javax.crypto.KeyGenerator

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

    @Provides
    fun provideWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(countryBoundsFactory)
            .build()
    }

    @Provides
    fun provideCountryBoundsDataSource(
        context: Context,
        moshi: Moshi
    ): CountryBoundsDataSource {
        return CountryBoundsDataSourceImpl(
            context = context,
            moshi = moshi,
            fileId = R.raw.coutry_bounds
        )
    }

    @Provides
    fun provideDemoCipherDataSource(): CipherDataSource {
        val keystore = KeyStore.getInstance(DemoCryptoConfig.KEYSTORE_NAME)
        val keygen = KeyGenerator.getInstance(DemoCryptoConfig.ALGORITHM, DemoCryptoConfig.KEYSTORE_NAME)

        return DemoCipherDataSource(keystore, keygen)
    }

    @Provides
    fun provideDemoDataStorage(
        context: Context,
        moshi: Moshi
    ): DemoDataStorage {
        return DemoDataStorageImpl(
            context = context,
            moshi = moshi,
            preferencesName = "Demo_Prefs"
        )
    }
}