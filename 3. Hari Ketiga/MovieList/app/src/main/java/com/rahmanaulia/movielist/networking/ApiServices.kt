package com.rahmanaulia.movielist.networking

object ApiServices {
    fun getMovieServices(): MovieApiServices{
        return RetrofitClient
            .getClient()
            .create(MovieApiServices::class.java)
    }
}