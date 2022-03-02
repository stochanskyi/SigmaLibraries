package com.stochanskyi.librariesdemo.app.di.modules

import dagger.Module

@Module(includes = [NetworkModule::class, DataBindsModule::class, RepositoryBindsModule::class, UseCaseBindsModule::class, ViewModelBindsModule::class, ImageLoadersBindsModule::class])
class AppModule