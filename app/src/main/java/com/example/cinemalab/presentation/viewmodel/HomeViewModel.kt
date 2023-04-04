package com.example.cinemalab.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.database.MovieDatabase
import com.example.cinemalab.data.cache.model.*
import com.example.cinemalab.data.cache.repository.MovieSource

class HomeViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(){
        val daoMovie = MovieDatabase.getInstance(context).getMovieDao()
        val daoGenre = MovieDatabase.getInstance(context).getGenreDao()
        val daoCountry = MovieDatabase.getInstance(context).getCountryDao()
        val daoMyTabOpt = MovieDatabase.getInstance(context).getMyTabOptDao()
        val daoProfile = MovieDatabase.getInstance(context).getProfileDao()
        MOVIE_SOURCE = MovieSource(daoMovie, daoGenre, daoCountry, daoMyTabOpt,daoProfile)
    }

    fun getFavMovies(): LiveData<List<FavMovie>> = MOVIE_SOURCE.favMovies
    fun getGenres(): LiveData<List<Genre>> = MOVIE_SOURCE.genres
    fun getCountries(): LiveData<List<Country>> = MOVIE_SOURCE.countries
    fun getMyTabOpt(): LiveData<List<MyTabOptions>> = MOVIE_SOURCE.myTabOptions
    fun getProfile(): LiveData<Profile> = MOVIE_SOURCE.profile

}