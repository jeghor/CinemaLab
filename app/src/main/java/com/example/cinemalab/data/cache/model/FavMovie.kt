package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movies")
data class FavMovie(
    @PrimaryKey
    val id: Int,
)
