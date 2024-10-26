package com.jmp.storage.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    private val sut: Converters = Converters()

    @Test
    fun `converting from filled json string to list`() {
        assertEquals(
            FILLED_FORMATTED_LIST,
            sut.fromString(FILLED_JSON_STRING)
        )
    }

    @Test
    fun `converting from filled list to json string`() {
        assertEquals(
            FILLED_JSON_STRING,
            sut.fromList(FILLED_FORMATTED_LIST)
        )
    }

    @Test
    fun `converting from empty json string to list`() {
        assertEquals(
            EMPTY_LIST,
            sut.fromString(EMPTY_JSON_STRING)
        )
    }

    @Test
    fun `converting from empty list to json string`() {
        assertEquals(
            EMPTY_JSON_STRING,
            sut.fromList(EMPTY_LIST)
        )
    }

    companion object {
        private const val FILLED_JSON_STRING = """["apple","banana","cherry"]"""
        private val FILLED_FORMATTED_LIST = listOf("apple", "banana", "cherry")
        private const val EMPTY_JSON_STRING = """[]"""
        private val EMPTY_LIST = emptyList<String>()
    }
}
