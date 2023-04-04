package com.example.cinemalab.data.cache.converter

import androidx.room.TypeConverter
import com.example.cinemalab.data.cache.model.MyTabOptions
import com.google.gson.Gson

class MyTabOptConverter {

    @TypeConverter
    fun optToJson(value: MyTabOptions): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToOpt(value: String) = Gson().fromJson(value, MyTabOptions::class.java) as MyTabOptions

}