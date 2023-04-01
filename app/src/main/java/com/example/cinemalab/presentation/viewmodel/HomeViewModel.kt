package com.example.cinemalab.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.database.MovieDatabase
import com.example.cinemalab.data.cache.model.FavMovie
import com.example.cinemalab.data.cache.repository.MovieSource

class HomeViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(){
        val daoMovie = MovieDatabase.getInstance(context).getMovieDao()
        MOVIE_SOURCE = MovieSource(daoMovie)
    }

    fun getFavMovies(): LiveData<List<FavMovie>> = MOVIE_SOURCE.favMovies

}