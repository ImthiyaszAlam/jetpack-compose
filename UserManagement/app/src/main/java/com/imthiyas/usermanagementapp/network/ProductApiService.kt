package com.imthiyas.usermanagementapp.network

import com.imthiyas.usermanagementapp.model.ProductResponse
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): ProductResponse
}