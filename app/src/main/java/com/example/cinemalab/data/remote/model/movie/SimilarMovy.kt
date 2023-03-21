package com.example.cinemalab.data.remote.model.movie

data class SimilarMovy(
    val alternativeName: String,
    val enName: Any,
    val id: Int,
    val name: String,
    val poster: Poster,
    val type: String
)