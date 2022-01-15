package com.kunal.fampay.data.network.models

import com.google.gson.annotations.SerializedName

data class CardGroups(
    val id:Int,
    val name:String,
    @SerializedName("design_type")
    val designType:String,
    val cards:List<Cards>,
    @SerializedName("is_scrollable")
    val isScrollable:Boolean,
    val height:Int
)
