package com.jcolom.kotlin_arch

import android.app.Application
import com.jcolom.kotlin_arch.di.component.ApplicationComponent
import com.jcolom.kotlin_arch.di.component.DaggerApplicationComponent
import com.jcolom.kotlin_arch.di.module.ApplicationModule

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class BaseApplication : Application() {

    internal lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
//        initGlide()
//        initCrashlytics()
//        initFont()
        initInjection()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    private fun initInjection() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }
}