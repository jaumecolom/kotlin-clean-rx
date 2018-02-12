package com.jcolom.kotlin_arch.domain.service

import retrofit2.Call
import retrofit2.Response

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface ServiceManager {

    fun execute(call: Call<*>): Response<*>

    fun checkResponseError(response:Response<*>)
}