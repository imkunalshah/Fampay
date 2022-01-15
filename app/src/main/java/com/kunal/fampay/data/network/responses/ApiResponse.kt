package com.kunal.fampay.data.network.responses

import com.google.gson.annotations.SerializedName
import com.kunal.fampay.data.network.models.CardGroups

data class ApiResponse(
    @SerializedName("card_groups")
    val cardGroups:List<CardGroups>
)
