package com.example.cinemalab.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalab.MOVIE_SOURCE
import com.example.cinemalab.data.cache.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    fun insert(profile: Profile){
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.insertProfile(profile)
        }
    }

    fun update(profile: Profile){
        viewModelScope.launch(Dispatchers.IO) {
            MOVIE_SOURCE.updateProfile(profile)
        }
    }

    fun delete(profile: Profile){
        viewModelScope.launch(Dispatchers.IO)    {
            MOVIE_SOURCE.deleteProfile(profile)
        }
    }

}