package com.jcolom.kotlin_arch

import com.jcolom.kotlin_arch.domain.command.main.DoCommandsMainImpl
import com.jcolom.kotlin_arch.domain.exceptions.BaseError
import com.jcolom.kotlin_arch.domain.command.base.PresenterCallback
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : PresenterCallback<List<String>, BaseError> {

    @Mock
    private lateinit var doCommandsMainImpl: DoCommandsMainImpl

    @Test
    fun addition_isCorrect() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
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
