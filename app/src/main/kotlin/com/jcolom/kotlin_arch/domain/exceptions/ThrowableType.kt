package com.jcolom.kotlin_arch.domain.exceptions

import android.content.Context
import android.text.TextUtils
import com.jcolom.kotlin_arch.R

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
object ThrowableType {

    val DEFAULT_ERROR = "DEFAULT_ERROR"


    fun parseError(context: Context, message: String): String {
        var errorParsed: String? = null

        if (!TextUtils.isEmpty(message)) {
            when (message) {
                DEFAULT_ERROR -> errorParsed = context.getString(R.string.error_default)
            }
        } else {
            errorParsed = context.getString(R.string.error_default)
        }

        return if (errorParsed == null) {
            message
        } else {
            errorParsed
        }

    }
}