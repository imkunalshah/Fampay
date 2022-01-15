package com.kunal.fampay.data.network.apis

import com.kunal.fampay.data.network.responses.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("04a04703-5557-4c84-a127-8c55335bb3b4")
    suspend fun fetchCards():Response<ApiResponse>

}