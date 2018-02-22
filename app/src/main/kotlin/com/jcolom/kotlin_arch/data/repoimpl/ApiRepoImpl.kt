package com.jcolom.kotlin_arch.data.repoimpl

import com.jcolom.kotlin_arch.data.serviceimpl.Service
import com.jcolom.kotlin_arch.data.serviceimpl.ServiceManagerImpl
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.service.ServiceGenerator
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

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
class ApiRepoImpl : ApiRepo {

    lateinit var service: Service
    private val serviceGenerator: ServiceGenerator
    private val serviceManager: ServiceManager

    @Inject constructor(serviceManager: ServiceManagerImpl, serviceGenerator: ServiceGenerator) {
        this.serviceGenerator = serviceGenerator
        this.serviceManager = serviceManager
        init()
    }

    fun init() {
        try {
            this.service = serviceGenerator.createService(Service::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getApiService(): Service {
        return service
    }

    override fun execute(call: Call<*>): Response<*> {
        return serviceManager.execute(call)
    }
}