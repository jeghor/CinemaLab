package com.example.cinemalab.ui.filter

data class Filter(
    val typeNumber : Int,
    val genres: ArrayList<String>,
    val country: ArrayList<String>,
    val year: String,
    val rating: String,
    val sortBy: String
)
