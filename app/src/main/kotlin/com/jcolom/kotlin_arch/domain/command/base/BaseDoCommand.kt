package com.jcolom.kotlin_arch.domain.command.base

import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface BaseDoCommand{
    fun setCallback(presenterCallback: PresenterCallback<*, BaseError>)
}