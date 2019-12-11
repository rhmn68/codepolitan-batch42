package com.rahmanaulia.daftartv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_tv.view.*

class TvAdapter(private val movies: List<ResultsItem>, private val listener: (ResultsItem) -> Unit)
    :RecyclerView.Adapter<TvAdapter.TvHolder>(){

    class TvHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(movie: ResultsItem, listener: (ResultsItem) -> Unit){
            itemView.tvTitleTv.text = movie.name

            val baseUrlImage = "https://image.tmdb.org/t/p/w300"
            val urlImage = baseUrlImage + movie.backdropPath
            Glide.with(itemView).load(urlImage).into(itemView.ivTv)

            val rating = movie.voteAverage?.div(2)
            if (rating != null) {
                itemView.ratingTv.rating = rating.toFloat()
            }

            itemView.tvRatingTv.text = rating.toString()

            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvHolder {
        return TvHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: TvHolder, position: Int) {
        holder.bindItem(movies[position], listener)
    }
}