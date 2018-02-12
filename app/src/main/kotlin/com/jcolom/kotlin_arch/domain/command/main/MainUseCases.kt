package com.jcolom.kotlin_arch.domain

import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainUseCases{

    val doCommandsMain: DoCommandsMain

    @Inject constructor(doCommandsMain:DoCommandsMain){
        this.doCommandsMain = doCommandsMain
    }

}