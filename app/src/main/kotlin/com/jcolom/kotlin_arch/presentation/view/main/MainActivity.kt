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

class MainActivity : BaseActivity(), MainPresenter.View {

    override val activityLayout: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var presenter: MainPresenter.Presenter

    private val getVersionButton by bind<Button>(R.id.button_getversion)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getVersionButton.setOnClickListener { presenter.doRequest() }
    }

    override fun initializePresenter() {
        setBasePresenter(presenter as BasePresenter)
    }

    override fun initializeInjection(component: ApplicationComponent) {
        component.plus(MainModule(this)).inject(this)
    }

    override fun onLoadedResponse(version: String?) {
        Toast.makeText(this, version, Toast.LENGTH_LONG).show()
    }
}
