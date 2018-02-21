package com.jcolom.kotlin_arch.presentation.view.main

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface MainPresenter {

    interface View {
        fun onLoadedResponse(version: String?)
        fun onConnectionError()
    }

    interface Presenter{
        fun doRequest()
    }
}