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
open class RxExecutorOperable : BaseSubscriber {

    private var presenterCallback: PresenterCallback<Any, BaseError>
    private var observable: Observable<*>? = null

    constructor(presenterCallback: PresenterCallback<*, BaseError>, observable: Observable<*>) {
        this.presenterCallback = presenterCallback as PresenterCallback<Any, BaseError>
        this.observable = observable
    }

    fun getObservable(): Observable<*> {
        return observable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun execute() {
        getObservable()?.subscribe(this)
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