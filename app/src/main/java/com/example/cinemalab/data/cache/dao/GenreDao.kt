package com.example.cinemalab.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemalab.data.cache.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * from genres")
    fun getAll(): LiveData<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: Genre)

}