package com.jcolom.kotlin_arch.presentation.view.base

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
abstract class BasePresenter : Presenter {

//    private var baseView: BaseView? = null

    override fun onPause() {
        onStop()
    }

    override fun onResume() {
        onStart()
    }

    override fun onDestroy() {
        onStop()
    }

    override fun onStart() {

    }

    override fun onStop() {
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: UnauthenticatedRuntimeException) {
//        if (baseView != null) {
//            baseView!!.forceRelogin()
//        } else {
//            showError(exception.getMessage())
//            navigateToLoginPage()
//        }
//    }

//    fun setView(baseView: BaseView) {
//        this.baseView = baseView
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: ResourceAccessException) {
//        showError("NO_CONNECTION")
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: Exception) {
//
//        if (exception is NullPointerException) {
//            navigateToLoginPage()
//        }
//        showError("NO_CONNECTION")
//
//    }

//    protected abstract fun navigateToLoginPage()
//
//    protected abstract fun showError(messageCode: String)

}
