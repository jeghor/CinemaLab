package com.example.cinemalab.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinemalab.data.cache.model.Country

@Dao
interface CountryDao {

    @Query("SELECT * from countries")
    fun getAll(): LiveData<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country)

}