package com.example.cinemalab.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    val name: String,
    val photo: String,
    @ColumnInfo(name = "fav_movies")
    val favMovies: List<FavMovie>,
    @ColumnInfo(name = "my_tab_options")
    val myTabOptions: MyTabOptions?
){
    @PrimaryKey
    var id: Int = 0
}
