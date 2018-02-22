package com.jcolom.kotlin_arch.di.module

import android.content.Context
import com.jcolom.kotlin_arch.BaseApplication
import com.jcolom.kotlin_arch.data.repoimpl.ApiRepoImpl
import com.jcolom.kotlin_arch.data.serviceimpl.ServiceManagerImpl
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.service.ServiceGenerator
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

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
@Module
class ApplicationModule(private val application: BaseApplication) {

    /**
     * Provides the application context
     *
     * @return application context
     */
    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return Executors.newFixedThreadPool(10)
    }

//    @Provides
//    @Singleton
//    internal fun providesSharedPreferences(sharedPreferenceManager: SharedPreferencesManagerImpl): SharedPreferencesManager {
//        return sharedPreferenceManager
//    }


//    @Provides
//    @Singleton
//    internal fun providesSessionManager(sessionManager: SessionManagerImpl): SessionManager {
//        return sessionManager
//    }
}
