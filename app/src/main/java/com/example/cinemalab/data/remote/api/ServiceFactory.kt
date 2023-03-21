package com.example.cinemalab.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.kinopoisk.dev"

object ServiceFactory {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: KinopoiskService by lazy {
        retrofit.create(KinopoiskService::class.java)
    }

}