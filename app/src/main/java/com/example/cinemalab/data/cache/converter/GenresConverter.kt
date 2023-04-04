package com.example.cinemalab.data.cache.converter

import androidx.room.TypeConverter
import com.example.cinemalab.data.cache.model.Genre
import com.google.gson.Gson

class GenresConverter {

    @TypeConverter
    fun listToJson(value: List<Genre>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value,Array<Genre>::class.java).toList()

}