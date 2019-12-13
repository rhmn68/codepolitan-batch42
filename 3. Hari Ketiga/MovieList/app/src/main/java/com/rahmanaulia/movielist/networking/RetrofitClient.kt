package com.rahmanaulia.movielist.networking

import com.rahmanaulia.movielist.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getClient(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_MOVIE + BuildConfig.API_VERSION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}