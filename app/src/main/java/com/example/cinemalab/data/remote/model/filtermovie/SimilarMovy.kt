package com.example.cinemalab.data.remote.model.filtermovie

data class SimilarMovy(
    val alternativeName: String,
    val enName: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val type: String
)