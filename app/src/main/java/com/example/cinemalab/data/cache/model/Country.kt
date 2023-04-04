package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @ColumnInfo(name = "country_name")
    val countryName: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
