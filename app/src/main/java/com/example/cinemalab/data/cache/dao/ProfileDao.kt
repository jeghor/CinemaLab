package com.example.cinemalab.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemalab.data.cache.model.Profile

@Dao
interface ProfileDao {

    @Query("SELECT * from profile")
    fun get(): LiveData<Profile>

    @Insert
    fun insert(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Delete
    fun delete(profile: Profile)

}