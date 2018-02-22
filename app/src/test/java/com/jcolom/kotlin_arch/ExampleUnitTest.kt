package com.jcolom.kotlin_arch

import com.jcolom.kotlin_arch.domain.command.main.DoCommandsMainImpl
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.presentation.view.base.PresenterCallback
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import rx.android.plugins.RxAndroidPlugins
import rx.plugins.RxJavaPlugins


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : PresenterCallback<List<String>, BaseError> {

    @Mock
    private lateinit var doCommandsMainImpl: DoCommandsMainImpl

    @BeforeClass
    fun setUpRxSchedulers() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

        try {
            base.evaluate()
        } finally {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }

    @Test
    fun addition_isCorrect() {

        doCommandsMainImpl.setCallback(this)
        doCommandsMainImpl.getListOne()
    }

    override fun onSuccess(response: List<String>) {
        var list = ArrayList<String>()
        list.add("One")
        list.add("Two")
        list.add("Three")
        assertEquals(list, response)
    }

    override fun onError(error: BaseError) {

    }

}
