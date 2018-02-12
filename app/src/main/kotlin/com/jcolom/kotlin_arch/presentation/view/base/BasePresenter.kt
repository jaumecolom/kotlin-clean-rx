package com.jcolom.kotlin_arch.presentation.view.base

import org.greenrobot.eventbus.EventBus

abstract class BasePresenter : Presenter {

//    private var baseView: BaseView? = null

    override fun onPause() {
        onStop()
    }

    override fun onResume() {
        onStart()
    }

    override fun onDestroy() {
        onStop()
    }

    /**
     * On start the presenter this method will register the object to the Bus.
     */
    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    /**
     * On stop the presenter this method will unregister the object to the Bus.
     */
    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: UnauthenticatedRuntimeException) {
//        if (baseView != null) {
//            baseView!!.forceRelogin()
//        } else {
//            showError(exception.getMessage())
//            navigateToLoginPage()
//        }
//    }

//    fun setView(baseView: BaseView) {
//        this.baseView = baseView
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: ResourceAccessException) {
//        showError("NO_CONNECTION")
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onEventMainThread(exception: Exception) {
//
//        if (exception is NullPointerException) {
//            navigateToLoginPage()
//        }
//        showError("NO_CONNECTION")
//
//    }

//    protected abstract fun navigateToLoginPage()
//
//    protected abstract fun showError(messageCode: String)

}
