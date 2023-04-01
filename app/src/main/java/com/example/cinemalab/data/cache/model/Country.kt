package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "countries")
data class Country(
    @ColumnInfo(name = "country_name")
    val countryName: String
)
