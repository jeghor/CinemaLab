package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.FavMovie
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.data.remote.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class InterestingViewModel : ViewModel() {

    private val repository = Repository()
    val movie: MutableLiveData<Response<FilterMovie>> = MutableLiveData()

    fun getMovie() {
        viewModelScope.launch {
            movie.value = repository.getHighestRatingMovies()
        }
    }

    fun getFilterMovie(
        typeNumber: Int,
        genres: ArrayList<String>,
        country: ArrayList<String>,
        year: String,
        rating: String,
        sortField: String
    ) {
        viewModelScope.launch {
            movie.value = repository.getFilterMovie(
                typeNumber, genres, country, year, rating, sortField
            )
        }
    }

    fun insert(favMovie: FavMovie, onSuccess:()-> Unit) = run {
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.insertMovieToFav(favMovie){
                onSuccess()
            }
        }
    }

}