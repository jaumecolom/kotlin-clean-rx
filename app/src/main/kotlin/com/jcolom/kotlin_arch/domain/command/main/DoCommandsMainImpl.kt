package com.jcolom.kotlin_arch.domain.command.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.command.base.BaseCommand
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import java.util.concurrent.Executor
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class DoCommandsMainImpl @Inject
constructor(executor: Executor, protected var mainRepo: MainRepo) : BaseCommand<DoCommandsMain.Event, Void?>(executor), DoCommandsMain {

    override fun useCaseJobExecution(void: Void?, resultEvent: DoCommandsMain.Event) {
        resultEvent.appVersion = mainRepo.getMainInfo()
    }
}
