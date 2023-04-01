package com.example.cinemalab.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinemalab.data.cache.dao.MovieDao
import com.example.cinemalab.data.cache.model.FavMovie

@Database(entities = [FavMovie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object{
        private var database: MovieDatabase ?= null

        @Synchronized
        fun getInstance(context: Context):MovieDatabase{
            return if (database == null){
                database = buildDatabase(context)
                database as MovieDatabase
            }else{
                database as MovieDatabase
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

}