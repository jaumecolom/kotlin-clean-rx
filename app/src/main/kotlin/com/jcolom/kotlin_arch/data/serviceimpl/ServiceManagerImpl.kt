package com.jcolom.kotlin_arch.data.serviceimpl

import android.util.Log
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import retrofit2.Call
import retrofit2.Response

/*
 * Copyright (C) 2018 Jaume Colom Ferrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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