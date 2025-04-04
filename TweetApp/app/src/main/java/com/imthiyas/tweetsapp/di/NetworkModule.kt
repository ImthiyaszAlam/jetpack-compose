package com.imthiyas.tweetsapp.di

import com.imthiyas.tweetsapp.network.TweetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    val BASE_URL = "https://api.jsonbin.io/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    fun providesTweetsApi(retrofit: Retrofit): TweetsApi {
        return retrofit.create(TweetsApi::class.java)
    }
}