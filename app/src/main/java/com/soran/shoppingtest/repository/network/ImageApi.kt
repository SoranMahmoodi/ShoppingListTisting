package com.soran.shoppingtest.repository.network

import com.soran.shoppingtest.BuildConfig
import com.soran.shoppingtest.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchImage: String,
        @Query("key") api: String = BuildConfig.API_KEY
    ): Response<ImageResponse>

}