package com.example.cinemalab.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cinemalab.data.remote.mapper.MovieEntityMapper
import com.example.cinemalab.data.remote.model.movie.Person
import com.example.cinemalab.databinding.FragmentMovieDetailBinding
import com.example.cinemalab.presentation.viewmodel.MovieDetailViewModel


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]


        val movieId = arguments?.getInt("MOVIE ID")
        val movieIndex = arguments?.getInt(("FILTER_MOVIE_ID"))

//        if (arguments?.getInt("MOVIE_ID") != null) {
//            viewModel.getMovieById(arguments?.getInt("MOVIE_ID")!!)
//        } else viewModel.getMovieById(arguments?.getInt(("FILTER_MOVIE_ID"))!!)

        //viewModel.getMovieById(arguments?.getInt("FILTER_MOVIE_ID")!!)

//        var movie: Movie? = null
//        viewModel.movie.observe(viewLifecycleOwner) {
//            movie = MovieEntityMapper().mapFromModel(it)!!
//        }

        viewModel.getMovieById(movieIndex!!)
        val mapper = MovieEntityMapper()
        viewModel.movie.observe(viewLifecycleOwner) {
            val movie = mapper.mapFromModel(it)!!

            var countries = ""
            val countrySize = movie?.countries?.size
            val lastIndexOfCountries = movie?.countries?.indexOf(movie?.countries!!.last())

            var genres = ""
            val genresSize = movie?.genres?.size
            val lastIndexOfGenres = movie?.genres?.indexOf(movie?.genres!!.last())

            with(binding) {
                Glide.with(posterImage)
                    .load(movie?.poster?.url)
                    .into(posterImage)

                movieName.text = movie?.name
                yearDetail.text = movie?.year.toString()

                if (countrySize != null) {
                    movie?.countries?.forEach {
                        countries += if (countrySize - 1 == lastIndexOfCountries) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
                countriesDetail.text = countries

                if (genresSize != null) {
                    movie?.genres?.forEach {
                        genres += if (genresSize - 1 == lastIndexOfGenres) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
                genresDetail.text = genres
                sloganDetail.text = movie?.slogan


                val persons = movie?.persons?.let { getPersons(it) }

                directorDetail.text = persons?.get(0) ?: ""
                producerDetail.text = persons?.get(1) ?: ""
                operatorDetail.text = persons?.get(2) ?: ""
                designerDetail.text = persons?.get(3) ?: ""
                editorDetail.text = persons?.get(4) ?: ""

                if (movie?.budget?.value != 0) {
                    budgetDetail.text =
                        movie?.budget?.value.toString() + movie?.budget?.currency
                } else budgetDetail.text = " "

                descriptionDetail.text = movie?.description
            }
        }


        binding.backArrow.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }

    private fun getPersons(person: List<Person>): List<String> {
        var director = ""
        var producer = ""
        var operator = ""
        var designer = ""
        var editor = ""
        var index = 0
        while (index < person.size) {
            when (person[index].enProfession) {
                "director" -> director += person[index].name + " "
                "producer" -> producer += person[index].name + " "
                "operator" -> operator += person[index].name + " "
                "designer" -> designer += person[index].name + " "
                "editor" -> editor += person[index].name + " "
            }

            index++
        }
        return listOf(director, producer, operator, designer, editor)
    }
}

