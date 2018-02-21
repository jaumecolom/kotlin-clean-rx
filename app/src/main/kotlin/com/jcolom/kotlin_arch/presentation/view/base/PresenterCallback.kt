package com.jcolom.kotlin_arch.presentation.view.base

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface PresenterCallback<Any, BaseError> {

    fun onSuccess(response: Any)

    fun onError(error: BaseError)
}