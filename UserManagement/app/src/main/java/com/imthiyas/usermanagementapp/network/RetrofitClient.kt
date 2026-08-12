package com.imthiyas.usermanagementapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()

    val api: ProductApiService =
        retrofit.create(ProductApiService::class.java)
}