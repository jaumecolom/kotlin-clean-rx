package com.jcolom.kotlin_arch.presentation.view.main

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.jcolom.kotlin_arch.R
import com.jcolom.kotlin_arch.di.component.ApplicationComponent
import com.jcolom.kotlin_arch.di.module.MainModule
import com.jcolom.kotlin_arch.presentation.view.base.BaseActivity
import com.jcolom.kotlin_arch.presentation.view.base.BasePresenter
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
class MainActivity : BaseActivity(), MainPresenter.View {

    override val activityLayout: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var presenter: MainPresenter.Presenter

    private val getVersionButton by bind<Button>(R.id.button_getversion)
    private val getListOneButton by bind<Button>(R.id.button_getlistone)
    private val getListTwoButton by bind<Button>(R.id.button_getlisttwo)
    private val getListConcatenateButton by bind<Button>(R.id.button_gettwolists_concatenate)
    private val getListMergedButton by bind<Button>(R.id.button_gettwolists_merged)
    private val buttonConcatenatedCalls by bind<Button>(R.id.button_concatenated_calls)
    private val buttonAllRequests by bind<Button>(R.id.button_allrequests)
    private val buttonGetResults by bind<Button>(R.id.button_get_results)
    var results = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getVersionButton.setOnClickListener { presenter.getVersion() }
        getListOneButton.setOnClickListener { presenter.getListOne() }
        getListTwoButton.setOnClickListener { presenter.getListTwo() }
        getListConcatenateButton.setOnClickListener { presenter.getListsConcatenated() }
        getListMergedButton.setOnClickListener { presenter.getListsMerged() }
        buttonConcatenatedCalls.setOnClickListener{ presenter.getConcatenatedCalls() }
        buttonAllRequests.setOnClickListener{
            presenter.getVersion()
            presenter.getListOne()
            presenter.getListTwo()
            presenter.getListsConcatenated()
            presenter.getListsMerged()
            presenter.getConcatenatedCalls()
        }
        buttonGetResults.setOnClickListener{
            Toast.makeText(this@MainActivity, results.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun initializePresenter() {
        setBasePresenter(presenter as BasePresenter)
    }

    override fun initializeInjection(component: ApplicationComponent) {
        component.plus(MainModule(this)).inject(this)
    }

    override fun onLoadedResponse(version: String?) {
        results++
        Toast.makeText(this, version, Toast.LENGTH_LONG).show()
    }

    override fun onListLoaded(response: List<String>) {
        results++
        var values = StringBuilder()
        for(s: String in response){
            values.append(s)
        }
        Toast.makeText(this, "List loaded: "+values, Toast.LENGTH_LONG).show()
    }

    override fun onConnectionError() {
        results++
        Toast.makeText(this, "Connection error!", Toast.LENGTH_LONG).show()
    }
}
