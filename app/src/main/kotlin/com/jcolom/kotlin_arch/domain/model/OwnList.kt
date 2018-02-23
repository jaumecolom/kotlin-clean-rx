package com.jcolom.kotlin_arch.domain.model

import com.jcolom.kotlin_arch.domain.command.base.BaseResponse

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class OwnList() : BaseResponse(){

    private var ownList: List<String>? = null

    constructor(ownList: List<String>?) : this() {
        this.ownList = ownList
    }

    fun getList(): List<String>? {
        return ownList
    }
}