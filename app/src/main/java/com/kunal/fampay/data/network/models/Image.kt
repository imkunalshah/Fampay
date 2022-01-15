package com.kunal.fampay.data.network.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("image_type")
    val imageType:String,
    @SerializedName("image_url")
    val imageUrl:String,
    @SerializedName("aspect_ratio")
    val aspectRatio:Float
)
