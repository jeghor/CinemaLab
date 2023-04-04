package com.example.cinemalab.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemalab.data.cache.model.MyTabOptions

@Dao
interface MyTabOptionsDao {

    @Query("SELECT * from my_tab_options")
    fun get(): LiveData<List<MyTabOptions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(options: MyTabOptions)

    @Delete
    fun delete(options: MyTabOptions)

}