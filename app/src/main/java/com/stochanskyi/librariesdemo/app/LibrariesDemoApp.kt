package com.stochanskyi.librariesdemo.app

import android.app.Application
import android.content.Context
import com.stochanskyi.librariesdemo.app.di.components.AppComponent
import com.stochanskyi.librariesdemo.app.di.components.DaggerAppComponent

class LibrariesDemoApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}

fun Context.appComponent(): AppComponent {
    return if (this is LibrariesDemoApp) appComponent
    else applicationContext.appComponent()
}