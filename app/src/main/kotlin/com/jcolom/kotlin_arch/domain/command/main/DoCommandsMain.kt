package com.jcolom.kotlin_arch.domain

import com.jcolom.kotlin_arch.domain.command.base.BaseEvent
import com.jcolom.kotlin_arch.domain.model.AppVersion

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface DoCommandsMain {

    abstract fun execute(parameters: Void?, resultEvent: Event)

    class Event : BaseEvent() {
        var appVersion: AppVersion? = null
    }

}