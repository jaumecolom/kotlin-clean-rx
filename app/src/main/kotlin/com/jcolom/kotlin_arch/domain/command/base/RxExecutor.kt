package com.jcolom.kotlin_arch.domain.command.base

import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.exceptions.ThrowableType
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
open class RxExecutor : BaseSubscriber {

    private var presenterCallback: PresenterCallback<Any, BaseError>

    constructor(presenterCallback: PresenterCallback<*, BaseError>) {
        this.presenterCallback = presenterCallback as PresenterCallback<Any, BaseError>
    }

    fun execute(observable: Observable<*>) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onNext(response: Any?) {
        presenterCallback.onSuccess(response as Any)
    }

    override fun onCompleted() {
        // Do nothing
    }

    override fun onError(e: Throwable?) {
        presenterCallback.onError(ThrowableType.parseError(e))
    }
}


