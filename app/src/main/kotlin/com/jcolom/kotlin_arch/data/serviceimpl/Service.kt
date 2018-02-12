package com.jcolom.kotlin_arch.data.serviceimpl

import com.jcolom.kotlin_arch.domain.model.AppVersion
import retrofit2.Call
import retrofit2.http.GET

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface Service {
    @get:GET(ServiceConstants.GET_VERSION)
    val version: Call<AppVersion>
}
