package com.jcolom.kotlin_arch.presentation.view.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.MainUseCases
import com.jcolom.kotlin_arch.presentation.view.base.BasePresenter
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainPresenterImpl : BasePresenter, MainPresenter.Presenter {

    var view: MainPresenter.View
    var useCases: MainUseCases

    @Inject constructor(useCases: MainUseCases, view: MainPresenter.View) {
        this.view = view
        this.useCases = useCases
    }

    override fun doRequest() {
        useCases.doCommandsMain.execute(null, DoCommandsMain.Event())
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: DoCommandsMain.Event) {
        view.onLoadedResponse(event.appVersion!!.getVersion())
    }

}