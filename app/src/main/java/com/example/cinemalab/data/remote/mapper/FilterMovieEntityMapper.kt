package com.example.cinemalab.data.remote.mapper

import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import retrofit2.Response

class FilterMovieEntityMapper : EntityMapper<Response<FilterMovie>, FilterMovie> {
    override fun mapFromModel(model: Response<FilterMovie>): FilterMovie {
        val movie = model.body()
        return FilterMovie(
            docs = movie?.docs,
            limit = movie?.limit,
            page = movie?.page,
            pages = movie?.pages,
            total = movie?.total
        )
    }
}