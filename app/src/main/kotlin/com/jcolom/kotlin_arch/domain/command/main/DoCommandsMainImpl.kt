package com.jcolom.kotlin_arch.domain.command.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.command.base.BaseDoCommandImpl
import com.jcolom.kotlin_arch.domain.command.base.RxExecutor
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import rx.Observable
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
class DoCommandsMainImpl @Inject
constructor(protected var mainRepo: MainRepo) : BaseDoCommandImpl(), DoCommandsMain {

    override fun setCallback(presenterCallback: PresenterCallback<*, BaseError>) {
        this.presenterCallback = presenterCallback
    }

    override fun getVersion() {
        RxExecutor(presenterCallback).execute(mainRepo.getMainInfo())
    }

    override fun getListOne() {
        RxExecutor(presenterCallback).execute(mainRepo.getListOne())
    }

    override fun getListTwo() {
        RxExecutor(presenterCallback).execute(mainRepo.getListTwo())
    }

    override fun getListsConcatenate() {
        RxExecutor(presenterCallback).execute(Observable.zip(mainRepo.getListOne(), mainRepo.getListTwo(),{a,b -> joinLists(a,b)}))
    }

    override fun getListsMerged() {
        RxExecutor(presenterCallback).execute(Observable.zip(mainRepo.getListOne(), mainRepo.getListTwo(), { a, b ->
            mergeLists(a, b)
        }))
    }

    private fun joinLists(a: List<String>?, b: List<String>?): List<String> {
        var listOne = ArrayList<String>(a)
        var listTwo = ArrayList<String>(b)
        listOne.addAll(listTwo)
        return listOne
    }

    private fun mergeLists(a: List<String>?, b: List<String>?): List<String> {
        var listOne = ArrayList<String>(a)
        var listTwo = ArrayList<String>(b)
        var mergedList = ArrayList<String>()
        var index = 0
        var merged = false
        while (!merged) {
            merged = true
            if (index < listOne.size) {
                mergedList.add(listOne.get(index))
                merged = false
            }
            if (index < listTwo.size) {
                mergedList.add(listTwo.get(index))
                merged = false
            }
            index++
        }
        return mergedList
    }
}
