package com.example.cinemalab.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.data.remote.model.movie.SimilarMovy
import com.example.cinemalab.databinding.ItemSimilarMovieBinding

class SimilarMovieAdapter : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    var movieList = listOf<SimilarMovy>()
        set(value) {
            field = value
            notifyItemInserted(movieList.size)
        }

    inner class SimilarMovieViewHolder(
        val binding: ItemSimilarMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSimilarMovieBinding.inflate(layoutInflater, parent, false)
        return SimilarMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val movie = movieList[position]

        with(holder.binding){
            Glide.with(posterImage)
                .load(movie.poster.previewUrl)
                .into(posterImage)

            movieName.text = movie.name
        }

    }

}