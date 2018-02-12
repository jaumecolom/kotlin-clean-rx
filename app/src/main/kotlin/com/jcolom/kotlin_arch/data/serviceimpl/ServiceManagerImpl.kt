package com.jcolom.kotlin_arch.data.serviceimpl

import android.text.TextUtils
import android.util.Log
import com.jcolom.kotlin_arch.data.util.GsonUtils
import com.jcolom.kotlin_arch.domain.exceptions.ServerError
import com.jcolom.kotlin_arch.domain.exceptions.ThrowableType
import com.jcolom.kotlin_arch.domain.service.ServiceManager
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

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
        var errorParsed = ThrowableType.DEFAULT_ERROR

        if (!response.isSuccessful && response.errorBody() != null) {

            val errorBody = response.errorBody()
            try {
                errorParsed = errorBody!!.string()
            } catch (e: IOException) {
//                Crashlytics.logException(e)
                e.printStackTrace()
            }

            if (!TextUtils.isEmpty(errorParsed)) {
                val serverError = GsonUtils.instance.stringToObject(errorParsed, ServerError::class.java)
                if (serverError != null) {
                    errorParsed = serverError.message.replace("\\\\n", "\n")
                }

                when (response.code()) {
                    400 -> {
                        Log.w(TAG, " $errorParsed- Bad Request.")
                        throw RuntimeException(errorParsed, Throwable("- Bad Request."))
                    }

                    500 -> {
                        Log.w(TAG, " $errorParsed- Internal Server Error.")
                        throw RuntimeException(errorParsed, Throwable("- Internal Server Error."))
                    }
                }
            }

            Log.w(TAG, " " + errorParsed)
            throw RuntimeException(errorParsed, Throwable("- Internal Server Error."))
        }
    }

}