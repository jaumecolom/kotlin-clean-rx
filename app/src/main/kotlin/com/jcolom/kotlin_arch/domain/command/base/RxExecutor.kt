package com.jcolom.kotlin_arch.domain.command.base

import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.exceptions.ThrowableType
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
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
class RxExecutor : BaseSubscriber {

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

    override fun onNext(t: Any) {
        presenterCallback.onSuccess(t)
    }

    override fun onError(e: Throwable) {
        presenterCallback.onError(ThrowableType.parseError(e))
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

}
