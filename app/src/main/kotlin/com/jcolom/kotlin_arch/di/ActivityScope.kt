package com.jcolom.kotlin_arch.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope
