package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.MyTabOptions
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.data.remote.repository.Repository
import com.example.cinemalab.ui.filter.Filter
import kotlinx.coroutines.launch
import retrofit2.Response

class MyTabViewModel: ViewModel() {

    private val repository = Repository()
    val movie: MutableLiveData<Response<FilterMovie>> = MutableLiveData()

    fun getFilterMovie(filter: Filter) {
        viewModelScope.launch {
            val response = repository.getFilterMovie(
                filter.typeNumber,
                filter.genres,
                filter.country,
                filter.year,
                filter.rating,
                filter.sortBy
            )
            movie.value = response
        }
    }

    fun getMyTabOpt(): LiveData<List<MyTabOptions>> = MOVIE_SOURCE.myTabOptions

}