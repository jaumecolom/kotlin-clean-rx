package com.jcolom.kotlin_arch.presentation.view.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import com.jcolom.kotlin_arch.domain.exceptions.ConnectionError
import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.presentation.view.base.BasePresenter
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainPresenterImpl @Inject constructor(var doCommandsMain: DoCommandsMain, var view: MainPresenter.View) : BasePresenter(), MainPresenter.Presenter, PresenterCallback<AppVersion, BaseError> {

    init {
        this.doCommandsMain.setCallback(this)
    }

    override fun doRequest() {
        doCommandsMain.getVersion()
    }

    override fun onSuccess(response: AppVersion) {
        view.onLoadedResponse(response.getVersion())
    }

    override fun onError(error: BaseError) {
        if(error is ConnectionError) {
            view.onConnectionError()
        }
    }
}