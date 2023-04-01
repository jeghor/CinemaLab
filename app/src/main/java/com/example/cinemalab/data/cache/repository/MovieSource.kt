package com.example.cinemalab.data.cache.repository

import androidx.lifecycle.LiveData
import com.example.cinemalab.data.cache.dao.MovieDao
import com.example.cinemalab.data.cache.model.FavMovie

class MovieSource(private val movieDao: MovieDao):MovieRepository {

    override val favMovies: LiveData<List<FavMovie>>
        get() = movieDao.getFavMovies()

    override suspend fun insertMovieToFav(favMovie: FavMovie, onSuccess: () -> Unit) {
        movieDao.insert(favMovie)
        onSuccess()
    }

    override suspend fun deleteMovieFromFav(favMovie: FavMovie, onSuccess: () -> Unit) {
        movieDao.delete(favMovie)
        onSuccess()
    }
}