package com.jcolom.kotlin_arch.data.repoimpl

import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainRepoImpl @Inject constructor(val apiRepo: ApiRepo) : MainRepo {

    override fun getMainInfo(): AppVersion {
        val call: Call<AppVersion> = apiRepo.getApiService().version
        val response: Response<*> = apiRepo.execute(call)
        var version: AppVersion = response.body() as AppVersion
        return version
    }

}