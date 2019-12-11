package com.rahmanaulia.latihanrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapterMovie()
    }

    private fun initAdapterMovie() {
        val dataMovie = Data(this).dataMovie()
        val adapter = MovieAdapter(dataMovie){movie->
            Toast.makeText(this, "title: ${movie.title}", Toast.LENGTH_SHORT).show()
            Intent(this, MainActivity::class.java).putExtra("", movie)
        }
        adapter.notifyDataSetChanged()

        rvMovieMain.layoutManager = LinearLayoutManager(this)
        rvMovieMain.adapter = adapter
    }
}
