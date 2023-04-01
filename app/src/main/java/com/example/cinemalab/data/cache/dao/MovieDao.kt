package com.example.cinemalab.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cinemalab.data.cache.model.FavMovie

@Dao
interface MovieDao {

    @Query("SELECT * from fav_movies")
    fun getFavMovies(): LiveData<List<FavMovie>>

    @Insert
    suspend fun insert(favMovie: FavMovie)

    @Delete
    suspend fun delete(favMovie: FavMovie)

}