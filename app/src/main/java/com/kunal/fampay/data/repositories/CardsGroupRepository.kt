package com.kunal.fampay.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunal.fampay.data.network.SafeApiRequest
import com.kunal.fampay.data.network.apis.ApiInterface
import com.kunal.fampay.data.network.models.CardGroups
import com.kunal.fampay.data.network.responses.ApiResponse

class CardsGroupRepository(
    private val api:ApiInterface
): SafeApiRequest() {


    private var cardGroups = MutableLiveData<List<CardGroups>>()

    suspend fun getCardGroups(): LiveData<List<CardGroups>> {
        fetchCardGroups()
        return cardGroups
    }

    private suspend fun fetchCardGroups(){
        val response = apiRequest { api.fetchCards() }
        cardGroups.value = response.cardGroups
    }
}