package com.rahmanaulia.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataApi()
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
        val adapter = movieResponse.results?.let { MovieAdapter(it){

        }}
        adapter?.notifyDataSetChanged()
        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapter
    }
}
