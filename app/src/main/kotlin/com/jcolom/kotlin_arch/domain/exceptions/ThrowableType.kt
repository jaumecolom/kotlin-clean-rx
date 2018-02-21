package com.jcolom.kotlin_arch.domain.exceptions

import java.net.UnknownHostException

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
object ThrowableType {

    fun parseError(t: Throwable?): BaseError {
        var error = BaseError()

        when (t) {
            is UnknownHostException -> error = ConnectionError()
//          is ...
        }
        return error
    }
}