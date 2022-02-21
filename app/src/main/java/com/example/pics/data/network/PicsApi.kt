package com.example.pics.data.network

import com.example.pics.data.network.response.PicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsApi {

    @GET("list")
    suspend fun getPics(@Query("limit") limit: Int): Response<PicsResponse>
}
