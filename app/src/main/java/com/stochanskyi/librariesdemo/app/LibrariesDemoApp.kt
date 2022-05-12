package com.stochanskyi.librariesdemo.app

import android.app.Application
import android.content.Context
import com.google.android.gms.maps.MapsInitializer
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.google.firebase.ktx.Firebase
import com.stochanskyi.librariesdemo.BuildConfig
import com.stochanskyi.librariesdemo.app.di.components.AppComponent
import com.stochanskyi.librariesdemo.app.di.components.DaggerAppComponent
import com.stochanskyi.librariesdemo.app.di.dependencies.AppDependencies
import timber.log.Timber

class LibrariesDemoApp : Application(), AppDependencies {

    lateinit var appComponent: AppComponent

    override val context: Context
        get() = this

    override fun onCreate() {
        super.onCreate()

        Firebase.crashlytics.setCustomKeys {
            key("build_type", BuildConfig.BUILD_TYPE)
        }

        Timber.plant(Timber.DebugTree())

        Timber.d("AppStarted")

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(this)
            .build()

        MapsInitializer.initialize(applicationContext, MapsInitializer.Renderer.LATEST, null)
    }
}

fun Context.appComponent(): AppComponent {
    return if (this is LibrariesDemoApp) appComponent
    else applicationContext.appComponent()
}