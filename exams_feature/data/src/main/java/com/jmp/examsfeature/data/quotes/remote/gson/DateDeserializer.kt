package com.jmp.examsfeature.data.quotes.remote.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateDeserializer : JsonDeserializer<Date> {
    private var formats: Array<String> = arrayOf()

    fun addDateFormat(format: String) {
        formats += format
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        json?.let { jsonElement ->
            formats.forEach { format ->
                try {
                    return SimpleDateFormat(format, Locale.getDefault())
                        .parse(jsonElement.asString) ?: throw ParseException("Null date", 0)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            throw JsonParseException("Unparseable date ${jsonElement.asString}")
        }
        throw JsonParseException("Null jsonElement parsing Date")
    }
}
