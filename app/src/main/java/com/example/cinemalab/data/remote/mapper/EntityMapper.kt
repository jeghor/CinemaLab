package com.example.cinemalab.data.remote.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E?

}