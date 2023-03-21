package com.example.cinemalab.data.remote.mapper

import com.example.cinemalab.data.remote.model.movie.Movie
import retrofit2.Response

class MovieEntityMapper : EntityMapper<Response<Movie>, Movie> {

    override fun mapFromModel(model: Response<Movie>): Movie? {
        val movie = model.body()
        return movie?.let {
            Movie(
                ageRating = it.ageRating,
                alternativeName = movie.alternativeName,
                backdrop = movie.backdrop,
                budget = movie.budget,
                collections = movie.collections,
                countries = movie.countries,
                createdAt = movie.createdAt,
                description = movie.description,
                enName = movie.enName,
                externalId = movie.externalId,
                facts = movie.facts,
                fees = movie.fees,
                genres = movie.genres,
                id = movie.id,
                movieLength = movie.movieLength,
                name = movie.name,
                names = movie.names,
                persons = movie.persons,
                poster = movie.poster,
                productionCompanies = movie.productionCompanies,
                rating = movie.rating,
                ratingMpaa = movie.ratingMpaa,
                releaseYears = movie.releaseYears,
                seasonsInfo = movie.seasonsInfo,
                sequelsAndPrequels = movie.sequelsAndPrequels,
                shortDescription = movie.shortDescription,
                similarMovies = movie.similarMovies,
                slogan = movie.slogan,
                spokenLanguages = movie.spokenLanguages,
                status = movie.status,
                top10 = movie.top10,
                top250 = movie.top250,
                type = movie.type,
                typeNumber = movie.typeNumber,
                updatedAt = movie.updatedAt,
                videos = movie.videos,
                votes = movie.votes,
                watchability = movie.watchability,
                year = movie.year
            )
        }
    }

}