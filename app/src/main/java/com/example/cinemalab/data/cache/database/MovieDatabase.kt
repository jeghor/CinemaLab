package com.example.cinemalab.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinemalab.data.cache.converter.CountriesConverter
import com.example.cinemalab.data.cache.converter.FavMovConverter
import com.example.cinemalab.data.cache.converter.GenresConverter
import com.example.cinemalab.data.cache.converter.MyTabOptConverter
import com.example.cinemalab.data.cache.dao.*
import com.example.cinemalab.data.cache.model.*

@Database(
    entities = [FavMovie::class, MyTabOptions::class, Genre::class, Country::class, Profile::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenresConverter::class,CountriesConverter::class, FavMovConverter::class, MyTabOptConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getGenreDao(): GenreDao
    abstract fun getCountryDao(): CountryDao
    abstract fun getMyTabOptDao(): MyTabOptionsDao

    abstract fun getProfileDao(): ProfileDao

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