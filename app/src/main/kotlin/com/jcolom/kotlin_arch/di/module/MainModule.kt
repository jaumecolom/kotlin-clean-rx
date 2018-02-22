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
