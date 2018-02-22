package com.jcolom.kotlin_arch.presentation.view.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.exceptions.ConnectionError
import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.presentation.view.base.BasePresenter
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import javax.inject.Inject

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class MainPresenterImpl @Inject constructor(var doCommandsMain: DoCommandsMain, var view: MainPresenter.View) : BasePresenter(), MainPresenter.Presenter, PresenterCallback<Any, BaseError> {

    init {
        this.doCommandsMain.setCallback(this)
    }

    override fun getVersion() {
        doCommandsMain.getVersion()
    }

    override fun getListOne() {
        doCommandsMain.getListOne()
    }

    override fun getListTwo() {
        doCommandsMain.getListTwo()
    }

    override fun getListsConcatenated() {
        doCommandsMain.getListsConcatenate()
    }

    override fun getListsMerged() {
        doCommandsMain.getListsMerged()
    }

    override fun onSuccess(response: Any) {
        if (response is AppVersion) {
            view.onLoadedResponse(response.getVersion())
        } else if (response is List<*>) {
            view.onListLoaded(response as List<String>)
        }
    }

    override fun onError(error: BaseError) {
        if (error is ConnectionError) {
            view.onConnectionError()
        }
    }
}