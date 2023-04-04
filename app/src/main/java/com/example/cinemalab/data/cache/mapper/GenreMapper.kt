package com.example.cinemalab.data.cache.mapper

import com.example.cinemalab.data.cache.model.Genre
import com.example.cinemalab.data.remote.mapper.EntityMapper

class GenreMapper: EntityMapper<ArrayList<String>,List<Genre>> {
    override fun mapFromModel(model: ArrayList<String>): List<Genre> {
        val list = arrayListOf<Genre>()
        model.forEach{
            list.add(Genre(it))
        }
        return list
    }
}