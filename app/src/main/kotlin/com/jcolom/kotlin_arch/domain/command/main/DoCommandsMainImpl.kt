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
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
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
