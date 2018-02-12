package com.jcolom.kotlin_arch.di.component

import com.jcolom.kotlin_arch.di.ActivityScope
import com.jcolom.kotlin_arch.di.module.MainModule
import com.jcolom.kotlin_arch.presentation.view.main.MainActivity
import dagger.Subcomponent

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */

@Subcomponent(modules = arrayOf(MainModule::class))
@ActivityScope
interface MainComponent {
    fun inject(app: MainActivity)
}