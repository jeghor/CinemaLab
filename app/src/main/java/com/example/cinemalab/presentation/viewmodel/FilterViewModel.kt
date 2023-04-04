package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.MyTabOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilterViewModel: ViewModel() {

    fun insertMyTabOpt(opt: MyTabOptions){
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.insertMyTubOpt(opt)
        }
    }

    fun deleteMyTabOpt(opt: MyTabOptions){
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.deleteMyTabOpt(opt)
        }
    }

}