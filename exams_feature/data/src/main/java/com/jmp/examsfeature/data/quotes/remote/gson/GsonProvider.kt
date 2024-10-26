package com.jmp.examsfeature.data.quotes.remote.gson

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.Date

object GsonProvider {

    private const val COMPLETE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val SLASHED_DATE_FORMAT = "yyyy/MM/dd"
    private const val HYPHENATED_DATE_FORMAT = "yyyy-MM-dd"

    fun makeGson(): Gson =
        DateDeserializer().run {
            addDateFormat(COMPLETE_DATE_FORMAT)
            addDateFormat(SLASHED_DATE_FORMAT)
            addDateFormat(HYPHENATED_DATE_FORMAT)

            GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Date::class.java, this)
                .setDateFormat(COMPLETE_DATE_FORMAT)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }
}
