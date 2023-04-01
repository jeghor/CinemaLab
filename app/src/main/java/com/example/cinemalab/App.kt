package com.example.cinemalab

import android.app.Application
import com.example.cinemalab.data.cache.model.FavMovie
import com.example.cinemalab.data.cache.repository.MovieSource
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.domain.model.OptionsService

class App:Application() {

    companion object{
        val optionsService = OptionsService()
        val filterMovieList = mutableListOf<Doc>()
        val favoritesMovieIdsList = mutableListOf<Int>()
        val favMovies = mutableListOf<FavMovie>()
        lateinit var source: MovieSource
    }


}