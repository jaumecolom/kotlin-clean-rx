package com.jcolom.kotlin_arch.domain.command.base

import org.greenrobot.eventbus.EventBus
import java.util.concurrent.Executor

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */

/**
 * Abstract class to represent all the use cases commands
 *
 * @param <T> Resulting event to be returned by the event bus
 * @param <P> Parameter to be used in the use case
</P></T> */
abstract class BaseCommand<T : BaseEvent, P>
/**
 * Constructor
 *
 * @param executor the executor where the use case will be executed.
 */
(
        /**
         * Reference to the global executor
         */
        protected var executor: Executor) {

    /**
     * Executes the useCase.
     *
     * @param parameters  the parameters
     * @param resultEvent the resulting event for the bus
     */
    fun execute(parameters: P, resultEvent: T) {
        val runnable = Runnable {
            try {
                useCaseJobExecution(parameters, resultEvent)
                EventBus.getDefault().post(resultEvent)
//            } catch (exeption: UnauthenticatedRuntimeException) {
//                EventBus.getDefault().post(exeption)
//            } catch (exeption: ResourceAccessException) {
//                EventBus.getDefault().post(exeption)
            } catch (e: Exception) {
                EventBus.getDefault().post(e)
            }
        }

        executor.execute(runnable)
    }

    /**
     * Implements the use case to be executed by the command
     *
     * @param parameters  to be used in the command
     * @param resultEvent resulting event to be used in the EventBus
     */
    protected abstract fun useCaseJobExecution(parameters: P, resultEvent: T)

}
