package com.kunal.fampay.data.network.models

import com.google.gson.annotations.SerializedName

data class Cards(
    val name:String,
    val title:String,
    @SerializedName("formatted_title")
    val formattedTitle:FormattedText,
    val description:String,
    @SerializedName("formatted_description")
    val formattedDescription:FormattedText,
    val icon: Image,
    @SerializedName("bg_image")
    val bgImage: Image,
    val url:String,
    @SerializedName("bg_color")
    val bgColor:String,
    val cta:List<CTA>
)
