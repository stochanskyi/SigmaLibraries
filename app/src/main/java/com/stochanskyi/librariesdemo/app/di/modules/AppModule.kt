package com.stochanskyi.librariesdemo.app.di.modules

import dagger.Module

@Module(includes = [NetworkModule::class, RepositoryBindsModule::class, UseCaseBindsModule::class, ViewModelBindsModule::class])
class AppModule