package com.jcolom.kotlin_arch.presentation.view.base

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.jcolom.kotlin_arch.BaseApplication
import com.jcolom.kotlin_arch.di.component.ApplicationComponent

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
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private val TAG = "BaseActivity"
    }

    private var basePresenter: BasePresenter? = null

    protected abstract val activityLayout: Int

    val applicationComponent: ApplicationComponent
        get() = (application as BaseApplication).getApplicationComponent()

    protected abstract fun initializePresenter()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjection(applicationComponent)
        setContentView(activityLayout)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        initializePresenter()
    }

    fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById(idRes) as T }
    }

    fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById(idRes) as T }
    }

    private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

    /**
     * @Param BaseApplication
     * @inheritDoc Inject must be done on initialization with the Activity
     */
    protected abstract fun initializeInjection(component: ApplicationComponent)

    fun setBasePresenter(basePresenter: BasePresenter) {
        this.basePresenter = basePresenter
    }

    private fun <T> checkNull(`object`: T?) {
        if (`object` == null) {
            throw RuntimeException(
                    "BasePresenter must be implemented by the activity. example: super.setBasePresenter(basePresenter);" + " \n Perform the proper injection of the ApplicationComponent")
        }
    }

    override fun onStart() {
        super.onStart()
        if (this.basePresenter != null) {
            this.basePresenter!!.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        checkNull(basePresenter)
        if (this.basePresenter != null) {
            this.basePresenter!!.onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.basePresenter != null) {
            this.basePresenter!!.onDestroy()
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
//    }

//    private fun sendViewAnalytics() {
//        Analytics.getInstance().sendViewAnalytics(this)
//    }

//    protected fun showMessageShort(activity: Activity, message: String) {
//        FeedBackUtil.showSnackBarShort(activity, message)
//    }

//    protected fun showErrorMessage(activity: Activity, message: String) {
//        var message = message
//        if (!TextUtils.isEmpty(message) && message.equals(ThrowableType.SESSION_EXPIRED, ignoreCase = true)) {
//            closeSession()
//        } else {
//            message = ThrowableType.parseError(activity, message)
//            FeedBackUtil.showSnackBarShort(activity, message)
//        }
//    }

//    protected fun showAlertMessage(message: String) {
//        FeedBackUtil.showAlertDialogWithButtons(this, message, getString(R.string.icar_sdk_btn_ok), null, false,
//                object : CallBack<Boolean>() {
//                    fun result(result: Boolean?) {}
//                })
//    }
//
//    protected fun showMessageShort(view: View, message: String) {
//        FeedBackUtil.showSnackBarShort(view, message)
//    }
//
//    protected fun showMessageLong(activity: Activity, message: String) {
//        FeedBackUtil.showSnackBarLong(activity, message)
//    }
//
//    protected fun showMessageLong(view: View, message: String) {
//        FeedBackUtil.showSnackBarLong(view, message)
//    }

}
