package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "my_tab_options")
data class MyTabOptions(
    val id: Int,
    @ColumnInfo(name = "type_number")
    val typeNumber: Int,
    val genres: ArrayList<String>,
    val countries: ArrayList<String>,
    val year: String,
    val rating: String,
    @ColumnInfo(name = "sort_field")
    val sortField: String
)
