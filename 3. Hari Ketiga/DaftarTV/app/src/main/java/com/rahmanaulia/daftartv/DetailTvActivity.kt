package com.rahmanaulia.daftartv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        getDataTv()
    }

    private fun getDataTv() {
        if (intent != null){
            val tvItem = intent.getParcelableExtra<ResultsItem>(EXTRA_TV)
            if (tvItem != null){
                setView(tvItem)
            }
        }
    }

    private fun setView(tvItem: ResultsItem?) {
        tvTitleTvDetail.text = tvItem?.originalName
        tvReleaseDateTvDetail.text = tvItem?.firstAirDate
        tvRatingTvDetail.text = tvItem?.voteAverage.toString()
        tvOverviewTvDetail.text = tvItem?.overview

        val baseUrlImage = "https://image.tmdb.org/t/p/w154"
        val urlImage = baseUrlImage + tvItem?.posterPath

        Glide.with(this).load(urlImage).into(ivTvDetail)
    }
}
