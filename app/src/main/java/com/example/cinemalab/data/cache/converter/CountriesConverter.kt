package com.example.cinemalab.data.cache.converter

import androidx.room.TypeConverter
import com.example.cinemalab.data.cache.model.Country
import com.google.gson.Gson

class CountriesConverter {

    @TypeConverter
    fun listToJson(value: List<Country>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Country>::class.java).toList()

}