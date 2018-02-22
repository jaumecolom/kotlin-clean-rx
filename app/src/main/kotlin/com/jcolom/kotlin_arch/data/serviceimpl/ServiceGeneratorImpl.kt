package com.jcolom.kotlin_arch.data.serviceimpl

import android.util.Log
import com.jcolom.kotlin_arch.BuildConfig
import com.jcolom.kotlin_arch.data.util.GsonUtils
import com.jcolom.kotlin_arch.domain.service.ServiceGenerator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.util.concurrent.TimeUnit

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
class ServiceGeneratorImpl : ServiceGenerator {

    lateinit var retrofit: Retrofit
    lateinit var service: Service

    override fun createService(serviceClass: Class<Service>): Service {

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(30000, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(30000, TimeUnit.MILLISECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())

//            addTokenAuthorization(requestBuilder, token)

            val request = requestBuilder.build()

            // try the request
            var response = chain.proceed(request)

            //logic to retry request on token expired:
            var tryCount = 0
            if (response.code() == 401 || response.code() == 403) {
                while (tryCount < 3 && !response.isSuccessful) {

                    Log.d("intercept", "Response not successful code: " + response.code() + " / " + response.body()!!.string())
                    Log.d("intercept", "Request is not successful - Retry: " + tryCount)
                    tryCount++

                    // rebuild the request and refresh token
                    val buildRequest = refreshTokenAndBuildNewRequest(original)

                    // retry the request
                    response = chain.proceed(buildRequest)

                }
            }

            response
        }

        val logging = HttpLoggingInterceptor()
        logging.setLevel(getLevelByBuild())
        httpClient.addInterceptor(logging)

        val client = httpClient.build()
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonUtils.customGsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
        service = retrofit.create(serviceClass)

        return service

    }

    private fun addTokenAuthorization(requestBuilder: Request.Builder) { //,token: AccessToken?) {

//        if (token != null && !token!!.getAccessToken().isEmpty()) {
//            val tokenAuthorization = token!!.getTokenType() + " " + token!!.getAccessToken()
//            requestBuilder.addHeader("Authorization", tokenAuthorization)
//        }
    }

    private fun refreshTokenAndBuildNewRequest(request: Request): Request {

        val requestBuilder = request.newBuilder()
                .header("Accept", "application/json")
                .method(request.method(), request.body())

//        val oldToken = tokenRepo.getToken()
//        val token = tokenRepo.refreshToken(oldToken)
//        addTokenAuthorization(requestBuilder, token)

        return requestBuilder.build()
    }


    private fun getLevelByBuild(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.LOG_ENABLED) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }

}