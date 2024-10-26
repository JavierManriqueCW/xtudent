package com.jmp.commons.utils.test

import org.awaitility.kotlin.await
import java.util.concurrent.TimeUnit

object TestUtils {

    private const val DEFAULT_WAIT_TIMEOUT: Long = 5

    fun waitUntil(
        timeout: Long = DEFAULT_WAIT_TIMEOUT,
        condition: () -> Boolean
    ) {
        await.atMost(timeout, TimeUnit.SECONDS).until { condition.invoke() }
    }
}
