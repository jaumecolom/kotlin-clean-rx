package com.jcolom.kotlin_arch.data.serviceimpl

import com.jcolom.kotlin_arch.data.repoimpl.ApiRepoImpl
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.service.ServiceGenerator
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import dagger.Module
import dagger.Provides
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
