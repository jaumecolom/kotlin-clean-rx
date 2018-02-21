package com.jcolom.kotlin_arch.di.module

import com.jcolom.kotlin_arch.data.repoimpl.MainRepoImpl
import com.jcolom.kotlin_arch.di.ActivityScope
import com.jcolom.kotlin_arch.domain.DoCommandsMain
import com.jcolom.kotlin_arch.domain.command.main.DoCommandsMainImpl
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import com.jcolom.kotlin_arch.presentation.view.main.MainPresenter
import com.jcolom.kotlin_arch.presentation.view.main.MainPresenterImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
@Module
class MainModule(private val view: MainPresenter.View) {

    @Provides
    @ActivityScope
    internal fun providesView(): MainPresenter.View {
        return view
    }

    @Provides
    @ActivityScope
    internal fun providesMainPresenter(presenter: MainPresenterImpl): MainPresenter.Presenter {
        return presenter
    }

    @Provides
    internal fun providesDoCommandsMain(mainRepo: MainRepoImpl): DoCommandsMain {
        return DoCommandsMainImpl(mainRepo)
    }

    @Provides
    @ActivityScope
    internal fun providesMainRepo(mainRepo: MainRepoImpl): MainRepo {
        return mainRepo
    }
}
