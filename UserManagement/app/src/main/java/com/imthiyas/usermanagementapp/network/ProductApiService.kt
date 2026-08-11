package com.imthiyas.usermanagementapp.network

import com.imthiyas.usermanagementapp.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductResponse
}