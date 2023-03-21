package com.example.cinemalab.data.remote.model.filtermovie

data class FilterMovie(
    val docs: List<Doc>?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?,
    val total: Int?
)