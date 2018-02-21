package com.jcolom.kotlin_arch.domain.repo

import com.jcolom.kotlin_arch.domain.model.AppVersion
import rx.Observable

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface MainRepo {
    fun getMainInfo() : Observable<AppVersion>
}