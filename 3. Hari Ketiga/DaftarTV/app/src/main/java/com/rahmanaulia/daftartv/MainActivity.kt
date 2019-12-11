package com.rahmanaulia.daftartv

import android.content.Intent
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
        val urlTv = "https://api.themoviedb.org/3/discover/tv?api_key=293c4fdf90af9919e3e39ea9e328ca37"

        val stringRequest = StringRequest(Request.Method.GET, urlTv,
            Response.Listener<String>{
                val tvResponse = Gson().fromJson(it, TvResponse::class.java)
                initAdapterTv(tvResponse)
            },Response.ErrorListener {
                Log.e("coba", "error: ${it.message}")
            })

        queue.add(stringRequest)
    }

    private fun initAdapterTv(tvResponse: TvResponse?) {
        val adapter = tvResponse?.results?.let { TvAdapter(it){resultItem ->
            val tvIntent = Intent(this, DetailTvActivity::class.java)
            tvIntent.putExtra(DetailTvActivity.EXTRA_TV, resultItem)
            startActivity(tvIntent)
        }}
        adapter?.notifyDataSetChanged()

        rvTv.layoutManager = LinearLayoutManager(this)
        rvTv.adapter = adapter
    }
}
