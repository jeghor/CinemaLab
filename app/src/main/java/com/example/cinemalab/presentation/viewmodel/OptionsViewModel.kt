package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.GENRES
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.Country
import com.example.cinemalab.data.cache.model.Genre
import com.example.cinemalab.data.remote.model.PossibleValuesByField
import com.example.cinemalab.data.remote.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class OptionsViewModel : ViewModel() {

    private val repository = Repository()
    val option: MutableLiveData<Response<PossibleValuesByField>> = MutableLiveData()

    fun getOptions(type: String) {
        viewModelScope.launch {
            if (type == GENRES) {
                option.value = repository.getPossibleValues("genres.name")
            } else {
                option.value = repository.getPossibleValues("countries.name")
            }
        }
    }

    fun insertGenre(genre: Genre) {
        viewModelScope.launch {
            MOVIE_SOURCE.insertGenre(genre)
        }
    }

    fun insertCountry(country: Country) {
        viewModelScope.launch {
            MOVIE_SOURCE.insertCountries(country)
        }
    }

}