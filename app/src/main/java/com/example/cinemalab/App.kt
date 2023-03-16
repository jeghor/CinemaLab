package com.example.cinemalab

import android.app.Application
import com.example.cinemalab.model.FactsService
import com.example.cinemalab.model.OptionsService

class App:Application() {

    companion object{
        val factsService = FactsService()
        val optionsService = OptionsService()
    }


}