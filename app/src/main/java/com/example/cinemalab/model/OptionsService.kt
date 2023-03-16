package com.example.cinemalab.model

typealias GenresListener = (country: List<Genre>) -> Unit
typealias CountryListener = (country: List<Country>) -> Unit

class OptionsService {

    private var genres = mutableListOf<Genre>(
        Genre("аниме"),
        Genre("биография"),
        Genre("боевик"),
        Genre("вестерн"),
        Genre("военный"),
        Genre("детектив"),
        Genre("детский"),
        Genre("для взрослых"),
        Genre("документальный"),
        Genre("драма"),
        Genre("игра"),
        Genre("история"),
        Genre("комедия"),
        Genre("концерт"),
        Genre("короткометражка"),
        Genre("криминал"),
        Genre("мелодрама"),
        Genre("музыка"),
        Genre("мультфильм"),
        Genre("мюзикл"),
        Genre("новости"),
        Genre("приключения"),
        Genre("реальное ТВ"),
        Genre("семейный"),
        Genre("спорт"),
        Genre("ток-шоу"),
        Genre("триллер"),
        Genre("ужасы"),
        Genre("фантастика"),
        Genre("фильм_нуар"),
        Genre("фэнтези"),
        Genre("церемония")
    )
    private var countries = mutableListOf<Country>(
        Country("Великобритания"),
        Country("Россия"),
        Country("СССР"),
        Country("США"),
        Country("Франция"),
    )

    private val genresListeners = mutableSetOf<GenresListener>()
    private val countriesListeners = mutableSetOf<CountryListener>()

    fun getGenres(): List<Genre> = genres
    fun getCountries(): List<Country> = countries

    fun getSelectedGenres():List<Genre>{
        val selectedGenres = mutableListOf<Genre>()
        genres.forEach{
            if (it.isSelected){
                selectedGenres.add(it)
            }
        }
        return selectedGenres
    }

    fun getSelectedCountries(): List<Country>{
        val selectedCountries = mutableListOf<Country>()
        countries.forEach{
            if (it.isSelected){
                selectedCountries.add(it)
            }
        }
        return selectedCountries
    }

    fun selectGenre(genre: Genre){
        genre.isSelected = true
    }
    fun selectCountry(country: Country){
        country.isSelected = true
    }

    fun clearGenresOptions(){
        genres.forEach{
            if (it.isSelected){
                it.isSelected = false
            }
        }
    }
    fun clearCountriesOptions(){
        countries.forEach{
            if (it.isSelected){
                it.isSelected = false
            }
        }
    }

    fun addGenresListener(listener: GenresListener){
        genresListeners.add(listener)
        listener.invoke(genres)
    }

    fun removeGenresListener(listener: GenresListener){
        genresListeners.remove(listener)
    }

    fun addCountryListener(listener: CountryListener){
        countriesListeners.add(listener)
        listener.invoke(countries)
    }

    fun removeCountryListener(listener: CountryListener){
        countriesListeners.remove(listener)
    }
}