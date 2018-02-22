package com.jcolom.kotlin_arch.presentation.view.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.exceptions.ConnectionError
import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.presentation.view.base.BasePresenter
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import javax.inject.Inject

/*
 * Copyright (C) 2018 Jaume Colom Ferrer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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