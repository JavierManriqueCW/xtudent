package com.jmp.examsfeature.data.quotes.remote.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("q")
    var text: String,
    @SerializedName("a")
    var author: String
)
