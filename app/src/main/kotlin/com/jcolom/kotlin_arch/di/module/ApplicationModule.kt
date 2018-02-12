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
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
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
