package com.example.cinemalab.ui.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.mapper.MovieEntityMapper
import com.example.cinemalab.data.remote.model.movie.Person
import com.example.cinemalab.data.remote.model.movie.SimilarMovy
import com.example.cinemalab.databinding.FragmentMovieDetailBinding
import com.example.cinemalab.presentation.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.*


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var actorsAdapter: ActorsAdapter
    private lateinit var similarAdapter: SimilarMovieAdapter
    private lateinit var factsAdapter: FactsAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        actorsAdapter = ActorsAdapter()
        similarAdapter = SimilarMovieAdapter(object : MovieActionListener{
            override fun onMovie(movie: SimilarMovy) {
                findNavController().navigate(R.id.movieDetailFragment,
                    bundleOf("FILTER_MOVIE_ID" to movie.id)
                )
            }

        })
        factsAdapter = FactsAdapter()
        binding.allUi.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE


        val movieId = arguments?.getInt("MOVIE ID")
        val movieIndex = arguments?.getInt(("FILTER_MOVIE_ID"))

        viewModel.getMovieById(movieIndex!!)

        CoroutineScope(Dispatchers.Main).launch {
            delay(800)
            if (viewModel.movie.value != null){
                binding.progressBar.visibility = View.GONE
                binding.allUi.visibility = View.VISIBLE
            }
        }
        binding.actorsRecyclerView.adapter = actorsAdapter
        binding.actorsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.similarMoviesRecyclerView.adapter = similarAdapter
        binding.similarMoviesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.factsRecyclerView.adapter = factsAdapter
        binding.factsRecyclerView.layoutManager = LinearLayoutManager(context)

        val mapper = MovieEntityMapper()
        viewModel.movie.observe(viewLifecycleOwner) {
            val movie = mapper.mapFromModel(it)!!

            var countries = ""
            val countrySize = movie.countries?.size
            val lastIndexOfCountries = movie.countries?.indexOf(movie.countries.last())

            var genres = ""
            val genresSize = movie.genres?.size
            val lastIndexOfGenres = movie.genres?.indexOf(movie.genres.last())

            with(binding) {
                Glide.with(posterImage)
                    .load(movie.poster?.url)
                    .into(posterImage)


                rating.text = String.format("%.1f",movie.rating?.kp)
                age.text = "${movie.ageRating}+"

                movieName.text = movie.name
                yearDetail.text = movie.year.toString()

                if (countrySize != null) {
                    movie.countries.forEach {
                        countries += if (countrySize - 1 == lastIndexOfCountries) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
                countriesDetail.text = countries

                if (genresSize != null) {
                    movie.genres.forEach {
                        genres += if (genresSize - 1 == lastIndexOfGenres) {
                            it.name + " "
                        } else {
                            it.name + ", "
                        }
                    }
                }
                genresDetail.text = genres
                sloganDetail.text = movie.slogan


                val persons = movie.persons?.let { getPersons(it) }

                directorDetail.text = persons?.get(0) ?: ""
                producerDetail.text = persons?.get(1) ?: ""
                operatorDetail.text = persons?.get(2) ?: ""
                designerDetail.text = persons?.get(3) ?: ""
                editorDetail.text = persons?.get(4) ?: ""

                if (movie.budget?.value != 0) {
                    budgetDetail.text =
                        movie.budget?.value.toString() + movie.budget?.currency
                } else budgetDetail.text = " "

                descriptionDetail.text = movie.description

                movie.persons?.forEach {
                    if (it.enProfession == "actor"){
                        actorsAdapter.actorsList.add(it)
                    }
                }

                feesUsaDetail.text = "${movie.fees?.usa?.value}${movie.fees?.usa?.currency}"
                feesWorldDetail.text = "${movie.fees?.world?.value}${movie.fees?.world?.currency}"

                similarAdapter.movieList = movie.similarMovies!!
                factsAdapter.factsList = movie.facts!!
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

