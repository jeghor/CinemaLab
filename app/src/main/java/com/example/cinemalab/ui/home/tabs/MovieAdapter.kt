package com.example.cinemalab.ui.home.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.databinding.ItemMovieBinding

interface MovieActionListener {
    fun onMovie(movie: Movie)
    fun onFavorites(movie: Movie)
}

class MovieAdapter(
    private val actionListener: MovieActionListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), View.OnClickListener {

    private var movieList = mutableListOf<Movie>()
        set(value) {
            field = value
            notifyItemInserted(movieList.size)
        }

    inner class MovieViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.favButton.setOnClickListener(this)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        with(holder.binding) {

            root.tag = movie
            favButton.tag = movie

            Glide.with(posterImage.context)
                .load(movie.poster?.previewUrl)
                .into(posterImage)

            movieName.text = movie.name
            val year = movie.year
            engMovieNameAndYear.text = "$year"

            var countries = ""
            val countrySize = movie.countries?.size
            val lastIndexOfCountries = movie.countries?.size?.minus(1)
            if (countrySize != null) {
                if (countrySize > 0) {
                    movie.countries.forEach {
                        countries += if (movie.countries.indexOf(it) == lastIndexOfCountries) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
            }
            country.text = countries

            var genres = ""
            val genresSize = movie.genres?.size
            val lastIndexOfGenres = movie.genres?.size?.minus(1)
            if (genresSize != null) {
                if (genresSize > 0) {
                    movie.genres.forEach {
                        genres += if (movie.genres.indexOf(it) == lastIndexOfGenres) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
            }
            genre.text = genres

            val ratingImdb = movie.rating!!.kp
            if (ratingImdb >= 8.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.green))
            } else if (8.0 > ratingImdb && ratingImdb >= 5.5) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.grass_green))
            } else if (5.5 > ratingImdb && ratingImdb >= 3.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.orange))
            } else if (3.0 > ratingImdb && ratingImdb > 0.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.red))
            } else rating.visibility = View.GONE
            rating.text = String.format("%.1f", ratingImdb)

            favButton.setBackgroundResource(R.drawable.favoriets_select)
        }
    }

    fun addMovie(movie: Movie) {
        movieList.add(movie)
        notifyItemInserted(movieList.size)
    }

    fun removeMovie(movie: Movie){
        val indexToDelete = movieList.indexOfFirst { it.id == movie.id }
        if (indexToDelete != -1){
            movieList.removeAt(indexToDelete)
            notifyItemRemoved(indexToDelete)
        }
    }

    override fun onClick(v: View) {
        val movie = v.tag as Movie
        val favButton: ImageView = v.findViewById(R.id.fav_button)
        when (v.id) {
            R.id.fav_button -> {
                actionListener.onFavorites(movie)
                favButton.setBackgroundResource(R.drawable.ic_bottom_nav_favorites)
            }
            else -> actionListener.onMovie(movie)
        }
    }

}