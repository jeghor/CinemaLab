package com.example.cinemalab.data.cache.converter

import androidx.room.TypeConverter
import com.example.cinemalab.data.cache.model.FavMovie
import com.google.gson.Gson

class FavMovConverter {

    @TypeConverter
    fun listToJson(value: List<FavMovie>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToFav(value: String) = Gson().fromJson(value, Array<FavMovie>::class.java).toList()

}