package com.kunal.fampay.data.network.models

import com.google.gson.annotations.SerializedName

data class CTA(
    val text:String,
    @SerializedName("bg_color")
    val bgColor:String,
    @SerializedName("text_color")
    val textColor:String,
    val url:String
)
