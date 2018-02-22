package com.jcolom.kotlin_arch.data.serviceimpl

import com.jcolom.kotlin_arch.domain.model.AppVersion
import retrofit2.http.GET
import rx.Observable

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface Service {
    @get:GET(ServiceConstants.GET_VERSION)
    val version: Observable<AppVersion>

    @get:GET(ServiceConstants.GET_LIST_ONE)
    val listone: Observable<List<String>>

    @get:GET(ServiceConstants.GET_LIST_TWO)
    val listtwo: Observable<List<String>>
}
