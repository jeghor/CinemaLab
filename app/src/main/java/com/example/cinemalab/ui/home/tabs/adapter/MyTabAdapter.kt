package com.example.cinemalab.ui.home.tabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.databinding.ItemMovieBinding

interface MovieActionListener {
    fun onMovie(movie: Movie)
    fun onFavorites(movie: Movie)
}

class MyTabAdapter(
    private val actionListener: MovieActionListener
) : RecyclerView.Adapter<MyTabAdapter.MyTabViewHolder>(), View.OnClickListener {

    private var movieList = mutableListOf<Movie>()

    inner class MyTabViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTabViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        binding.root.setOnClickListener(this)
        return MyTabViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MyTabViewHolder, position: Int) {
        val movie = movieList[position]

        holder.binding.root.tag = movie

        with(holder.binding) {

            Glide.with(posterImage.context)
                .load(movie.poster?.previewUrl)
                .into(posterImage)

            movieName.text = movie.name

            val year = movie.year
            engMovieNameAndYear.text = "$year"

            var countries = ""
            val countrySize = movie.countries?.size
            val lastIndexOfCountries = movie.countries?.indexOf(movie.countries.last())
            if (countrySize != null) {
                movie.countries.forEach {
                    countries += if (countrySize - 1 == lastIndexOfCountries) {
                        it.name + " "
                    } else {
                        it.name + ", "
                    }
                }
            }
            country.text = countries

            var genres = ""
            val genresSize = movie.genres?.size
            val lastIndexOfGenres = movie.genres?.indexOf(movie.genres.last())
            if (genresSize != null) {
                movie.genres.forEach {
                    genres += if (genresSize - 1 == lastIndexOfGenres) {
                        it.name + " "
                    } else {
                        it.name + ", "
                    }
                }
            }
            genre.text = genres

            val ratingImdb = movie.rating?.imdb
            if (ratingImdb!! >= 8.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.green))
            } else if (8.0 > ratingImdb && ratingImdb >= 5.5) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.grass_green))
            } else if (5.5 > ratingImdb && ratingImdb >= 3.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.orange))
            } else if (3.0 > ratingImdb && ratingImdb > 0.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.red))
            } else rating.background.setTint(holder.binding.root.context.getColor(R.color.white))
            rating.text = ratingImdb.toString()
        }
    }

    fun setList(list: MutableList<Movie>) {
        movieList = list
        notifyItemInserted(movieList.size)
    }

    fun addMovie(movie: Movie) {
        movieList.add(movie)
        notifyItemInserted(movieList.size)
    }

    override fun onClick(v: View) {
        val movie = v.tag as Movie
        actionListener.onMovie(movie)
    }

}