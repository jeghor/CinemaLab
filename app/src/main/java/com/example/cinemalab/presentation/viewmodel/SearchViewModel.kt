package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.data.remote.repository.Repository
import com.example.cinemalab.ui.filter.Filter
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel() : ViewModel() {

    private val repository = Repository()
    val movie: MutableLiveData<Response<FilterMovie>> = MutableLiveData()

//    fun getFilterMovie(filter: Filter) {
//        viewModelScope.launch {
//            val response = repository.getFilterMovie(
//                filter.typeNumber,
//                country = filter.country[0],
//                genres = filter.year,
//                filter.genres[0]
//            )
//            movie.value = response
//        }
//    }

}