package com.example.cinemalab.data.remote.model.movie

data class Votes(
    val await: Int,
    val filmCritics: Int,
    val imdb: Int,
    val kp: Int,
    val russianFilmCritics: Int
)