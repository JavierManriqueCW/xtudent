package com.jmp.commons.utils.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class UnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()
}
