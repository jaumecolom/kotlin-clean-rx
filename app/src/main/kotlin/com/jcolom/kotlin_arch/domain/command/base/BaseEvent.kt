package com.jcolom.kotlin_arch.domain.command.base

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
open class BaseEvent {

    var isError = false
    lateinit var errorMessage: String
}
