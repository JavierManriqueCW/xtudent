package com.jmp.exams_feature.data.quotes.remote

import com.google.gson.FieldNamingPolicy
import com.jmp.examsfeature.data.quotes.remote.gson.GsonProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.Date

class GsonProviderTest {

    @Test
    fun `makeGson returns a non-null Gson object`() {
        assertNotNull(
            GsonProvider.makeGson()
        )
    }

    @Test
    fun `makeGson sets field naming policy to LOWER_CASE_WITH_UNDERSCORES`() {
        assertEquals(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES,
            GsonProvider.makeGson().fieldNamingStrategy()
        )
    }

    @Test
    fun `makeGson registers Date type adapter`() {
        assertNotNull(
            GsonProvider
                .makeGson()
                .getAdapter(Date::class.java)
        )
    }

    @Test
    fun `makeGson allows lenient parsing`() {
        assertEquals(
            NUMERIC_VALUE,
            GsonProvider
                .makeGson()
                .fromJson(STRING_VALUE, Int::class.java)
                .toInt()
        )
    }

    @Test
    fun `makeGson sets correct date formats`() {
        val gson = GsonProvider.makeGson()
        val dateFormats = listOf(
            COMPLETE_DATE_FORMAT,
            SLASHED_DATE_FORMAT,
            HYPHENATED_DATE_FORMAT
        )

        dateFormats.forEach {
            assertNotNull(gson.fromJson(it, Date::class.java))
        }
    }

    companion object {
        private const val COMPLETE_DATE_FORMAT = "\"2023-09-18T12:45:30.123Z\""
        private const val SLASHED_DATE_FORMAT = "\"2023/09/18\""
        private const val HYPHENATED_DATE_FORMAT = "\"2023-09-18\""

        private const val NUMERIC_VALUE = 123
        private const val STRING_VALUE = "123"
    }
}
