package com.example.cinemalab.data.remote.model.filtermovie

data class Rating(
    val await: Double,
    val filmCritics: Double,
    val imdb: Double,
    val kp: Double,
    val russianFilmCritics: Double,
    val tmdb: Double
)