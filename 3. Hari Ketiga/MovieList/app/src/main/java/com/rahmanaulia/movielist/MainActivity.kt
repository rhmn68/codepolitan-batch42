package com.rahmanaulia.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.rahmanaulia.movielist.networking.ApiServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataApiFromRetrofit()
        initSwipe()
    }

    private fun initSwipe() {

    }

    private fun getDataApiFromRetrofit() {
        ApiServices.getMovieServices()
            .getMovie(BuildConfig.API_KEY)
            .enqueue(object : Callback<MovieResponse>{
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "message: ${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: retrofit2.Response<MovieResponse>
                ) {
                    if (response.isSuccessful){
                        val responseMovie = response.body()
                        if (responseMovie != null){
                            initAdapterMovie(responseMovie)
                        }
                    }
                }

            })
    }

    private fun getDataApi() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=293c4fdf90af9919e3e39ea9e328ca37"

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{response ->
                val movieResponse = Gson().fromJson(response, MovieResponse::class.java)
                initAdapterMovie(movieResponse)
            }, Response.ErrorListener {
                Log.e("coba", "error: ${it.message}")
            })

        queue.add(stringRequest)
    }

    private fun initAdapterMovie(movieResponse: MovieResponse) {
        val adapter = movieResponse.results?.let { MovieAdapter(it){resultItem ->
            val detailMovieIntent = Intent(this, DetailMovieActivity::class.java)
            detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, resultItem)
            startActivity(detailMovieIntent)
        }}
        adapter?.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapter
    }
}
