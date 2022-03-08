package com.stochanskyi.librariesdemo.app.di.modules

import android.content.Context
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideActivityRecognitionClient(context: Context): ActivityRecognitionClient {
        return ActivityRecognition.getClient(context)
    }
}