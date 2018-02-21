package com.jcolom.kotlin_arch.domain.command.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.command.base.BaseDoCommandImpl
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import com.jcolom.kotlin_arch.domain.command.base.RxExecutor
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class DoCommandsMainImpl @Inject
constructor(protected var mainRepo: MainRepo) : BaseDoCommandImpl(), DoCommandsMain {

    override fun setCallback(presenterCallback: PresenterCallback<*, BaseError>) {
        this.presenterCallback = presenterCallback
    }

    override fun getVersion() {
        RxExecutor(presenterCallback).execute(mainRepo.getMainInfo().filter({true}))

        var rxHelper = RxExecutor(presenterCallback, mainRepo.getMainInfo())
        rxHelper.getObservable().filter({true})
        rxHelper.execute()

    }
}
