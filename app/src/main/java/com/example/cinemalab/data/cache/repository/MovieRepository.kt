package com.example.cinemalab.data.cache.repository

import androidx.lifecycle.LiveData
import com.example.cinemalab.data.cache.model.FavMovie

interface MovieRepository {

    val favMovies: LiveData<List<FavMovie>>

    suspend fun insertMovieToFav(favMovie: FavMovie, onSuccess:() -> Unit)
    suspend fun deleteMovieFromFav(favMovie: FavMovie, onSuccess:() -> Unit)

}