package com.jcolom.kotlin_arch.domain.command.base

import rx.Subscriber

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
open class BaseSubscriber : Subscriber<Any>(){
    override fun onNext(t: Any?) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}