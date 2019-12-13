package com.rahmanaulia.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        getDataMovie()
    }

    private fun getDataMovie() {
        if (intent != null){
            val movieItem = intent.getParcelableExtra<ResultsItem>(EXTRA_MOVIE)
            if (movieItem != null){
                setViewDetail(movieItem)
            }
        }
    }

    private fun setViewDetail(movieItem: ResultsItem) {
        tvTitleDetailMovie.text = movieItem.title
        tvReleaseDate.text = movieItem.releaseDate
        tvOverviewDetailMovie.text = movieItem.overview

        val baseUrlImage = "https://image.tmdb.org/t/p/w300"
        val urlImage = baseUrlImage + movieItem.backdropPath

        Glide.with(this).load(urlImage).into(ivDetailMovie)
    }
}
