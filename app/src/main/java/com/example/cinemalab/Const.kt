package com.example.cinemalab

import com.example.cinemalab.data.cache.model.MyTabOptions
import com.example.cinemalab.data.cache.model.Profile
import com.example.cinemalab.data.cache.repository.MovieSource
import com.example.cinemalab.domain.model.Option

const val TYPE_NUMBER = "TYPE_NUMBER"
const val GENRES = "GENRES"
const val COUNTRY = "COUNTRY"
const val YEAR = "YEAR"
const val RATING = "RATING"
const val SORT_BY = "SORT_BY"
const val DESTINATION = "DESTINATION"
const val OPTION_TYPE = "OPTION_TYPE"

const val IN_FAVORITES = "IN_FAVORITES"
const val MOVIE_ID = "MOVIE_ID"

const val MY_TAB = "MY_TAB"


lateinit var MOVIE_SOURCE: MovieSource
val GENRES_LIST = mutableListOf<Option>()
val COUNTRIES_LIST = mutableListOf<Option>()
var MY_TAB_OPTIONS: MyTabOptions? = null
var PROFILE: Profile? = null