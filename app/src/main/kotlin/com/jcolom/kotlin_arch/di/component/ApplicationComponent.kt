package com.jcolom.kotlin_arch.di.component

import com.jcolom.kotlin_arch.BaseApplication
import com.jcolom.kotlin_arch.data.serviceimpl.ServiceModule
import com.jcolom.kotlin_arch.di.module.ApplicationModule
import com.jcolom.kotlin_arch.di.module.MainModule
import dagger.Component
import javax.inject.Singleton

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ServiceModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApplication)
    fun plus(module: MainModule): MainComponent
}
