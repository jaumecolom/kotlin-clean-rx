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
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
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