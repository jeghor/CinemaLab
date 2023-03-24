package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.data.remote.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    private val repository = Repository()
    val movie: MutableLiveData<Response<Movie>> = MutableLiveData()

    fun getMovieById(id: Int){
        viewModelScope.launch {
            movie.value = repository.getMovieById(id)
        }
    }
}