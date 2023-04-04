package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @ColumnInfo(name = "genre_name")
    val genreName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
