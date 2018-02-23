package com.jcolom.kotlin_arch.domain.command.main

import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.command.base.BaseDoCommandImpl
import com.jcolom.kotlin_arch.domain.command.base.PresenterCallback
import com.jcolom.kotlin_arch.domain.command.base.RxExecutor
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.model.OwnList
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
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
        RxExecutor(presenterCallback).execute(mainRepo.getListOne().map { a -> OwnList(a) })
    }

    override fun getListTwo() {
        RxExecutor(presenterCallback).execute(mainRepo.getListTwo().map { a -> OwnList(a) })
    }

    override fun getListsConcatenate() {
        RxExecutor(presenterCallback).execute(Observable.zip<OwnList, OwnList, OwnList>(
                mainRepo.getListOne().map { a -> OwnList(a) },
                mainRepo.getListTwo().map { b -> OwnList(b) },
                BiFunction<OwnList, OwnList, OwnList>
                { listOne, listTwo -> joinLists(listOne, listTwo) }))
    }

    override fun getListsMerged() {
        RxExecutor(presenterCallback).execute(Observable.zip<OwnList, OwnList, OwnList>(
                mainRepo.getListOne().map { a -> OwnList(a) },
                mainRepo.getListTwo().map { b -> OwnList(b) },
                BiFunction<OwnList, OwnList, OwnList>
                { listOne, listTwo -> mergeLists(listOne, listTwo) }))
    }

    override fun getConcatenatedCalls() {
        RxExecutor(presenterCallback).execute(mainRepo.getListOne()
                        .map { list -> list.get(0) }
                        .flatMap { firstValue -> mainRepo.getSubListOf(firstValue) }.map { a -> OwnList(a) })
    }

    private fun joinLists(a: OwnList, b: OwnList): OwnList {
        var listOne = ArrayList<String>(a.getList())
        var listTwo = ArrayList<String>(b.getList())
        listOne.addAll(listTwo)
        return OwnList(listOne)
    }

    private fun mergeLists(a: OwnList, b: OwnList): OwnList {
        var listOne = ArrayList<String>(a.getList())
        var listTwo = ArrayList<String>(b.getList())
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
        return OwnList(mergedList)
    }
}
