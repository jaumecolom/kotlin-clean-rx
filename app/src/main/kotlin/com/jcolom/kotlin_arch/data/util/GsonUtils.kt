package com.jcolom.kotlin_arch.data.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

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
class GsonUtils private constructor() {

    private val gson = Gson()
    private val TAG = "GsonUtils"

    fun <T> stringToObject(jsonString: String, _class: Class<T>): T? {
        try {
            return gson.fromJson(jsonString, _class)
        } catch (e: Exception) {
            Log.e(TAG, " " + _class.canonicalName, e)
        }

        return null
    }

    fun <T> stringToObjectList(jsonString: String, collectionType: Type): T? {
        try {
            return gson.fromJson<T>(jsonString, collectionType)
        } catch (e: Exception) {
            Log.e(TAG, " ", e)
        }

        return null
    }

    fun objectToString(`object`: Any): String? {
        try {
            return gson.toJson(`object`)
        } catch (e: Exception) {
            Log.e(TAG, " ", e)
        }

        return null
    }

    fun fromObjectToJSONObject(`object`: Any): JSONObject? {

        try {
            return JSONObject(gson.toJson(`object`))
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }

    }

    companion object {
        private var sharedPreferencesUtil: GsonUtils? = null

        val instance: GsonUtils
            @Synchronized get() {
                if (sharedPreferencesUtil == null) {
                    sharedPreferencesUtil = GsonUtils()
                }
                return sharedPreferencesUtil as GsonUtils
            }

        val customGsonConverterFactory: GsonConverterFactory
            get() {

                val gson = GsonBuilder().create()
                return GsonConverterFactory.create(gson)
            }
    }
}
