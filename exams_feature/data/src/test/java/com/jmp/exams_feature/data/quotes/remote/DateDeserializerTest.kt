package com.jmp.exams_feature.data.quotes.remote

import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.jmp.examsfeature.data.quotes.remote.gson.DateDeserializer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class DateDeserializerTest {

    private val sut: DateDeserializer =
        DateDeserializer()

    @Test
    fun `deserializing a valid string date returns a date object`() {
        sut.addDateFormat(YEAR_MONTH_DAY_FORMAT)
        val json = JsonPrimitive(YEAR_MONTH_DAY_VALUE)
        val expectedDate = SimpleDateFormat(YEAR_MONTH_DAY_FORMAT, Locale.getDefault()).parse(
            YEAR_MONTH_DAY_VALUE
        )
        val actualDate = sut.deserialize(json, null, null)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserializing an invalid date throws an exception`() {
        sut.addDateFormat(YEAR_MONTH_DAY_FORMAT)
        val json = JsonPrimitive(INVALID_DATE_VALUE)
        assertThrows(JsonParseException::class.java) {
            sut.deserialize(json, null, null)
        }
    }

    @Test
    fun `deserializing a null json throws an exception`() {
        assertThrows(JsonParseException::class.java) {
            sut.deserialize(null, null, null)
        }
    }

    @Test
    fun `deserializing after adding multiple formats returns a date`() {
        sut.addDateFormat(YEAR_MONTH_DAY_FORMAT)
        sut.addDateFormat(DAY_MONTH_YEAR_FORMAT)
        val json = JsonPrimitive(DAY_MONTH_YEAR_VALUE)
        val expectedDate = SimpleDateFormat(DAY_MONTH_YEAR_FORMAT, Locale.getDefault()).parse(
            DAY_MONTH_YEAR_VALUE
        )
        val actualDate = sut.deserialize(json, null, null)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserializing with no matching format throws an exception`() {
        sut.addDateFormat(YEAR_MONTH_DAY_FORMAT)
        val json = JsonPrimitive(DAY_MONTH_YEAR_VALUE)
        assertThrows(JsonParseException::class.java) {
            sut.deserialize(json, null, null)
        }
    }

    companion object {
        private const val YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd"
        private const val DAY_MONTH_YEAR_FORMAT = "dd/MM/yyyy"
        private const val YEAR_MONTH_DAY_VALUE = "2023-11-10"
        private const val DAY_MONTH_YEAR_VALUE = "10/11/2023"
        private const val INVALID_DATE_VALUE = "invalid date"
    }
}
