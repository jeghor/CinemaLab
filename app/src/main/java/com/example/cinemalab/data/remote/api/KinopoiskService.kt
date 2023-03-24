package com.example.cinemalab.data.remote.api

import com.example.cinemalab.data.remote.model.PossibleValuesByField
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.data.remote.model.movie.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val TOKEN = "B73XC5H-N6QMSBN-G9WM5YF-KKJVGMD"

interface KinopoiskService {

    @GET("/v1/movie/possible-values-by-field?token=$TOKEN")
    suspend fun getPossibleValues(@Query("field") field: String): Response<PossibleValuesByField>

    @GET("/v1/movie/random?token=$TOKEN")
    suspend fun getRandomMovie(): Response<Movie>

    @GET("/v1/movie/{id}?token=$TOKEN")
    suspend fun getMovieById(@Path("id") movieId: Int): Response<Movie>

    @GET("/v1/movie?limit=20&token=$TOKEN")
    suspend fun getFilterMovie(
        @Query("typeNumber") typeNumber: Int,
        @Query("countries.name") country: String,
        @Query("year") year: Int,
        @Query("genres.name") vararg genres: String,
    ): Response<FilterMovie>

    @GET("/v1/movie?limit=30&token=$TOKEN")
    suspend fun getFilterMovie(
        @Query("typeNumber") typeNumber: Int,
        @Query("genres.name") genres: ArrayList<String>,
        @Query("countries.name") country: ArrayList<String>,
        @Query("year") year: String,
        @Query("rating.kp") rating: String,
        @Query("sortField") sortField: String
    ): Response<FilterMovie>

    @GET("/v1/movie?sortField=rating.kp&sortType=-1&limit=10&token=$TOKEN")
    suspend fun getHighestRatingMovies():Response<FilterMovie>

    @GET("/v1/movie?year=2020-2023&sortField=year&sortType=-1&limit=10&token=$TOKEN")
    suspend fun getLatestMovies():Response<FilterMovie>
}