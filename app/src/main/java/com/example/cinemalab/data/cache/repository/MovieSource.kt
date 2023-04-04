package com.example.cinemalab.data.cache.repository

import androidx.lifecycle.LiveData
import com.example.cinemalab.data.cache.dao.*
import com.example.cinemalab.data.cache.model.*

class MovieSource(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val countryDao: CountryDao,
    private val myTabOptDao: MyTabOptionsDao,
    private val profileDao: ProfileDao
) : MovieRepository {

    override val favMovies: LiveData<List<FavMovie>>
        get() = movieDao.getFavMovies()
    override val genres: LiveData<List<Genre>>
        get() = genreDao.getAll()
    override val countries: LiveData<List<Country>>
        get() = countryDao.getAll()
    override val myTabOptions: LiveData<List<MyTabOptions>>
        get() = myTabOptDao.get()
    override val profile: LiveData<Profile>
        get() = profileDao.get()

    override suspend fun insertProfile(profile: Profile) {
        profileDao.insert(profile)
    }

    override suspend fun updateProfile(profile: Profile) {
        profileDao.update(profile)
    }

    override suspend fun deleteProfile(profile: Profile) {
        profileDao.delete(profile)
    }

    override suspend fun insertMovieToFav(favMovie: FavMovie, onSuccess: () -> Unit) {
        movieDao.insert(favMovie)
        onSuccess()
    }

    override suspend fun deleteMovieFromFav(favMovie: FavMovie, onSuccess: () -> Unit) {
        movieDao.delete(favMovie)
        onSuccess()
    }

    override suspend fun insertGenre(genre: Genre) {
        genreDao.insert(genre)
    }

    override suspend fun insertCountries(country: Country) {
        countryDao.insert(country)
    }

    override suspend fun insertMyTubOpt(options: MyTabOptions) {
        myTabOptDao.insert(options)
    }

    override suspend fun deleteMyTabOpt(options: MyTabOptions) {
        myTabOptDao.delete(options)
    }

}