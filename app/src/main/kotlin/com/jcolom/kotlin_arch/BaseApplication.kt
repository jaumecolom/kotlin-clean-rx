package com.jcolom.kotlin_arch

import android.app.Application
import com.jcolom.kotlin_arch.di.component.ApplicationComponent
import com.jcolom.kotlin_arch.di.component.DaggerApplicationComponent
import com.jcolom.kotlin_arch.di.module.ApplicationModule

/*
 * Copyright (C) 2018 Jaume Colom Ferrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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