package com.example.cinemalab.data.cache.repository

import androidx.lifecycle.LiveData
import com.example.cinemalab.data.cache.model.*

interface MovieRepository {

    val favMovies: LiveData<List<FavMovie>>
    val genres: LiveData<List<Genre>>
    val countries: LiveData<List<Country>>
    val myTabOptions: LiveData<List<MyTabOptions>>
    val profile: LiveData<Profile>

    suspend fun insertProfile(profile: Profile)
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(profile: Profile)

    suspend fun insertMovieToFav(favMovie: FavMovie, onSuccess:() -> Unit)
    suspend fun deleteMovieFromFav(favMovie: FavMovie, onSuccess:() -> Unit)

    suspend fun insertGenre(genre: Genre)
    suspend fun insertCountries(country: Country)
    suspend fun insertMyTubOpt(options: MyTabOptions)
    suspend fun deleteMyTabOpt(options: MyTabOptions)

}