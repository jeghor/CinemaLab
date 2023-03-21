package com.example.cinemalab.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.ItemMovieBinding

interface MovieActionListener {
    fun onMovie(movie: Doc)
    fun onFavorites(movie: Doc)
}

class SearchAdapter(
    private val actionListener: MovieActionListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), View.OnClickListener {

    var movieList = mutableListOf<Doc>()

    inner class SearchViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.favButton.setOnClickListener(this)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.root.tag = movie
        holder.binding.favButton.tag = movie

        with(holder.binding) {
            if (movie.poster != null){
                Glide.with(posterImage.context)
                    .load(movie.poster.previewUrl)
                    .into(posterImage)
            } else {
                Glide.with(posterImage.context)
                    .load("https://cdn.vectorstock.com/i/preview-1x/94/73/glitched-error-message-art-typographic-poster-glit-vector-11189473.webp")
                    .into(posterImage)
            }


            movieName.text = movie.name
            val year = movie.year
            engMovieNameAndYear.text = "$year"

            var countries = ""
            val countrySize = movie.countries.size
            val lastIndexOfCountries = movie.countries.indexOf(movie.countries.last())
            if (countrySize > 0) {
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
            val genresSize = movie.genres.size
            val lastIndexOfGenres = movie.genres.indexOf(movie.genres.last())
            if (genresSize > 0) {
                movie.genres.forEach {
                    genres += if (genresSize - 1 == lastIndexOfGenres) {
                        it.name + " "
                    } else {
                        it.name + ", "
                    }
                }
            }
            genre.text = genres

            val ratingImdb = movie.rating.imdb
            if (ratingImdb >= 8.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.green))
            } else if (8.0 > ratingImdb && ratingImdb >= 5.5) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.grass_green))
            } else if (5.5 > ratingImdb && ratingImdb >= 3.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.orange))
            } else if (3.0 > ratingImdb && ratingImdb > 0.0) {
                rating.background.setTint(holder.binding.root.context.getColor(R.color.red))
            }else rating.background.setTint(holder.binding.root.context.getColor(R.color.white))
            rating.text = ratingImdb.toString()

            if (movie.inFavorites){
                favButton.setBackgroundResource(R.drawable.favoriets_select)
            }
        }
    }

    fun setList(list: List<Doc>?) {
        movieList = list as MutableList<Doc>
        notifyItemInserted(movieList.size)
    }

    override fun onClick(v: View) {
        val movie = v.tag as Doc
        when(v.id){
            R.id.fav_button -> actionListener.onFavorites(movie)
            else -> actionListener.onMovie(movie)
        }
    }

}