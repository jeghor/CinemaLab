package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.FavMovie
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.data.remote.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FavoritesViewModel: ViewModel() {

    private val repository = Repository()
    val movie: MutableLiveData<Response<Movie>> = MutableLiveData()

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            movie.value = repository.getMovieById(id)
        }
    }

    fun delete(favMovie: FavMovie, onSuccess:()-> Unit) = run {
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.deleteMovieFromFav(favMovie){
                onSuccess()
            }
        }
    }

}