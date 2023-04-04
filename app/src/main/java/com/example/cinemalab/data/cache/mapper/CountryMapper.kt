package com.example.cinemalab.data.cache.mapper

import com.example.cinemalab.data.cache.model.Country
import com.example.cinemalab.data.remote.mapper.EntityMapper

class CountryMapper: EntityMapper<ArrayList<String>, List<Country>> {
    override fun mapFromModel(model: ArrayList<String>): List<Country> {
        val list = arrayListOf<Country>()
        model.forEach{
            list.add(Country(it))
        }
        return list
    }


}