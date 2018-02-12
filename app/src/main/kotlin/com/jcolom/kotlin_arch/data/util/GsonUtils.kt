package com.jcolom.kotlin_arch.data.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */

/**
 * @author afebrer
 * @since 09/08/16
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
