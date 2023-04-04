package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_tab_options")
data class MyTabOptions(
    @ColumnInfo(name = "type_number")
    val typeNumber: Int,
    val genres: List<Genre>,
    val countries: List<Country>,
    val year: String,
    val rating: String,
    @ColumnInfo(name = "sort_field")
    val sortField: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
