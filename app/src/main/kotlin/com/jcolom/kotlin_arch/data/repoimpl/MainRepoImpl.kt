package com.jcolom.kotlin_arch.data.repoimpl

import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.domain.model.OwnList
import com.jcolom.kotlin_arch.domain.repo.ApiRepo
import com.jcolom.kotlin_arch.domain.repo.MainRepo
import io.reactivex.Observable
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
class MainRepoImpl @Inject constructor(val apiRepo: ApiRepo) : MainRepo {

    override fun getMainInfo(): Observable<AppVersion> {
        return apiRepo.getApiService().version
    }

    override fun getListOne(): Observable<List<String>> {
        return apiRepo.getApiService().listone
    }

    override fun getListTwo(): Observable<List<String>> {
        return apiRepo.getApiService().listtwo
    }

    override fun getSubListOf(value: String): Observable<List<String>> {
        return apiRepo.getApiService().getSubListOf()
    }

}