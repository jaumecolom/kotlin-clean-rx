package com.jcolom.kotlin_arch.domain.service

import com.jcolom.kotlin_arch.data.serviceimpl.Service

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface ServiceGenerator{

    @Throws(Exception::class)
    fun createService(serviceClass: Class<Service>): Service

}