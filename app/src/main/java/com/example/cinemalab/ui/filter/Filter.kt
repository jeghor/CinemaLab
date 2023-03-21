package com.example.cinemalab.ui.filter

data class Filter(
    val typeNumber : Int,
    val genres: MutableList<String>,
    val country: String,
    val year: Int,
    val sortBy: String
)
