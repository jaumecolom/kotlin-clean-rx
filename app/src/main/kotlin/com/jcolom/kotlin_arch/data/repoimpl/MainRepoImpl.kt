package com.jcolom.kotlin_arch.data.repoimpl

import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import rx.Observable
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainRepoImpl @Inject constructor(val apiRepo: ApiRepo) : MainRepo {

    override fun getMainInfo(): Observable<AppVersion> {
        return apiRepo.getApiService().version
    }

    override fun getListOne(): Observable<List<String>> {
        return apiRepo.getApiService().listone
    }

    override fun getListTwo(): Observable<List<String>> {
        return apiRepo.getApiService().listtwo
    }

}