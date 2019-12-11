package com.rahmanaulia.latihanrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val listMovie: List<Movie>,
                   private val listener: (Movie) -> Unit)
    :RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(movie: Movie, listener: (Movie) -> Unit) {
            itemView.tvTitleMovie.text = movie.title
            itemView.tvDescMovie.text = movie.desc
            movie.image?.let { itemView.ivMovie.setImageResource(it) }

            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listMovie[position], listener)
    }
}