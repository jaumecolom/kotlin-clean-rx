package com.jcolom.kotlin_arch.data.serviceimpl

import android.util.Log
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import retrofit2.Call
import retrofit2.Response

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class ServiceManagerImpl : ServiceManager {

    companion object {
        const val TAG = "ServiceManagerImpl"
    }

    override fun execute(call: Call<*>): Response<*> {
        val response: Response<*>

        try {
            response = call.execute()
        } catch (e: Exception) {
            throw RuntimeException(e.message, e)
        }
        checkResponseError(response)

        return response
    }

    override fun checkResponseError(response: Response<*>) {

        if (!response.isSuccessful && response.errorBody() != null) {
            val errorBody = response.errorBody()
            Log.w(TAG, " " + errorBody.toString())
            throw RuntimeException(errorBody.toString(), Throwable(response.code().toString()))
        }
    }

}