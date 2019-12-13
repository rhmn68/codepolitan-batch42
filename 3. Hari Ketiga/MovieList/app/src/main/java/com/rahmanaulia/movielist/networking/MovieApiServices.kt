package com.rahmanaulia.movielist.networking

import com.rahmanaulia.movielist.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiServices {
    @GET("discover/movie")
    fun getMovie(@Query("api_key") apiKey: String)
        :Call<MovieResponse>

    @GET("discover/tv")
    fun getTv(@Query("api_key") apiKey: String)
            :Call<MovieResponse>
}