package com.stochanskyi.librariesdemo.app

import android.app.Application
import android.content.Context
import com.stochanskyi.librariesdemo.app.di.components.AppComponent
import com.stochanskyi.librariesdemo.app.di.components.DaggerAppComponent
import com.stochanskyi.librariesdemo.app.di.dependencies.AppDependencies
import timber.log.Timber

class LibrariesDemoApp : Application(), AppDependencies {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        Timber.d("AppStarted")

        appComponent = DaggerAppComponent
            .builder()
            .appDependencies(this)
            .build()
    }

    override val context: Context
        get() = this
}

fun Context.appComponent(): AppComponent {
    return if (this is LibrariesDemoApp) appComponent
    else applicationContext.appComponent()
}