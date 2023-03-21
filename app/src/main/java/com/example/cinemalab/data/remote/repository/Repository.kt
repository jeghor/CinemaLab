package com.example.cinemalab.data.remote.repository

import com.example.cinemalab.data.remote.api.ServiceFactory
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.data.remote.model.movie.Movie
import retrofit2.Response

class Repository {

    suspend fun getRandomMovie(): Response<Movie> = ServiceFactory.api.getRandomMovie()

    suspend fun getMovieById(movieId: Int): Response<Movie> =
        ServiceFactory.api.getMovieById(movieId)

    suspend fun getFilterMovie(
        typeNumber: Int,
        country: String,
        year: Int,
        vararg genres: String
    ): Response<FilterMovie> = ServiceFactory.api.getFilterMovie(
        typeNumber,
        country,
        year,
        *genres,
    )

    suspend fun getHighestRatingMovies(): Response<FilterMovie> =
        ServiceFactory.api.getHighestRatingMovies()

    suspend fun getLatestMovies(): Response<FilterMovie> = ServiceFactory.api.getLatestMovies()
}