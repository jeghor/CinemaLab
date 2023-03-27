package com.example.cinemalab.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.data.remote.model.movie.SimilarMovy
import com.example.cinemalab.databinding.ItemSimilarMovieBinding

interface MovieActionListener{
    fun onMovie(movie: SimilarMovy)
}

class SimilarMovieAdapter(
    private val actionListener: MovieActionListener
) : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>(),View.OnClickListener {

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
        binding.root.setOnClickListener(this)
        return SimilarMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val movie = movieList[position]
        with(holder.binding){
            root.tag = movie
            Glide.with(posterImage)
                .load(movie.poster.previewUrl)
                .into(posterImage)

            movieName.text = movie.name
        }

    }

    override fun onClick(v: View) {
        val movie = v.tag as SimilarMovy
        actionListener.onMovie(movie)
    }

}