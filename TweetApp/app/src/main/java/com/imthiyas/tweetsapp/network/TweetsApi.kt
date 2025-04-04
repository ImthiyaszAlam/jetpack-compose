package com.imthiyas.tweetsapp.network

import com.imthiyas.tweetsapp.models.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {


    @GET("/v3/b/67cdaaa7ad19ca34f8192a34?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>

    @GET("/v3/b/67cdaaa7ad19ca34f8192a34?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<Tweet>>

}