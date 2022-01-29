package com.stochanskyi.librariesdemo.app.di.components

import com.stochanskyi.librariesdemo.app.di.modules.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent