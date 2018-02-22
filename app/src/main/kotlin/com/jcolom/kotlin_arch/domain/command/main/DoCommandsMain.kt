package com.jcolom.kotlin_arch.domain

import com.jcolom.kotlin_arch.domain.command.base.BaseDoCommand

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface DoCommandsMain : BaseDoCommand {
    fun getVersion()
    fun getListOne()
    fun getListTwo()
    fun getListsConcatenate()
    fun getListsMerged()
}