package com.jcolom.kotlin_arch.domain.command.base

import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.exceptions.ThrowableType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
class RxExecutorOperable : BaseSubscriber {

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

    override fun onNext(response: Any) {
        presenterCallback.onSuccess(response)
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        presenterCallback.onError(ThrowableType.parseError(e))
    }

    override fun onSubscribe(d: Disposable) {
    }
}