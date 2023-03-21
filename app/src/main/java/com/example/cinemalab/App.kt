package com.example.cinemalab

import android.app.Application
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.model.FactsService
import com.example.cinemalab.model.OptionsService

class App:Application() {

    companion object{
        val factsService = FactsService()
        val optionsService = OptionsService()
        val filterMovieList = mutableListOf<Doc>()
        val favoritesMovieIdsList = mutableListOf<Int>()
    }


}