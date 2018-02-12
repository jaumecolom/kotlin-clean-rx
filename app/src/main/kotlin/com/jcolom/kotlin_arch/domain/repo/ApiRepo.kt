package com.jcolom.kotlin_arch.domain.repo

import com.jcolom.kotlin_arch.data.serviceimpl.Service
import retrofit2.Call
import retrofit2.Response

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface ApiRepo {
    abstract fun getApiService(): Service
    abstract fun execute(call: Call<*>): Response<*>
}