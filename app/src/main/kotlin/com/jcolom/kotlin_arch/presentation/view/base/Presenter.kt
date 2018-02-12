package com.jcolom.kotlin_arch.presentation.view.base

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
interface Presenter {

    fun onPause()

    fun onResume()

    fun onDestroy()

    fun onStart()

    fun onStop()
}
