package com.rahmanaulia.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val listMovie: List<ResultsItem>,
                   private val listener: (ResultsItem) -> Unit)
    :RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

    class MovieHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(item: ResultsItem, listener: (ResultsItem) -> Unit){
            itemView.tvTitleMovie.text = item.title
            itemView.tvDescMovie.text = item.overview

            val rating = item.voteAverage?.div(2)
            if (rating != null) {
                itemView.ratingMovie.rating = rating.toFloat()
            }
            itemView.tvRating.text = rating.toString()

            val baseUrlImage = "https://image.tmdb.org/t/p/w154"
            val urlImage = baseUrlImage + item.posterPath
            Glide.with(itemView).load(urlImage).into(itemView.ivMovie)

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindItem(listMovie[position], listener)
    }
}