package com.jcolom.kotlin_arch.domain.repo

import com.jcolom.kotlin_arch.domain.model.AppVersion
import com.jcolom.kotlin_arch.domain.model.OwnList
import io.reactivex.Observable

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
interface MainRepo {
    fun getMainInfo() : Observable<AppVersion>

    fun getListOne() : Observable<List<String>>

    fun getListTwo() : Observable<List<String>>

    fun getSubListOf(value: String) : Observable<List<String>>
}