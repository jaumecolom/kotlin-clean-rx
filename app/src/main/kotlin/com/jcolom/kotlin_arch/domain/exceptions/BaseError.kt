package com.jcolom.kotlin_arch.domain.exceptions

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
open class BaseError {
    //TODO: Define depending on the API or Data provider
    var isError = false
    lateinit var errorMessage: String
}
