package com.jcolom.kotlin_arch.data.serviceimpl

import com.jcolom.kotlin_arch.data.repoimpl.ApiRepoImpl
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.service.ServiceGenerator
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
@Module
class ServiceModule {

    @Provides
    @Singleton
    internal fun providesApiRepo(): ApiRepo {
        return ApiRepoImpl(serviceManager = ServiceManagerImpl(), serviceGenerator = ServiceGeneratorImpl())
    }

//    @Provides
//    @Singleton
//    internal fun providesServiceManager(serviceManager: ServiceManagerImpl): ServiceManager {
//        return serviceManager
//    }
//
//    @Provides
//    @Singleton
//    internal fun providesServiceGenerator(serviceGenerator: ServiceGeneratorImpl): ServiceGenerator {
//        return serviceGenerator
//    }

//    @Provides
//    @Singleton
//    internal fun providesTokenRepo(tokenRepo: TokenRepoImpl): TokenRepo {
//        return tokenRepo
//    }
}
