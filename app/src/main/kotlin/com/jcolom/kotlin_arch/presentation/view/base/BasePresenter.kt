package com.jcolom.kotlin_arch.presentation.view.base

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

    override fun onStart() {

    }

    override fun onStop() {
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
