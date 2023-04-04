package com.example.cinemalab.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.*
import com.example.cinemalab.data.cache.model.FavMovie
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.FragmentMyTabBinding
import com.example.cinemalab.presentation.viewmodel.InterestingViewModel
import com.example.cinemalab.presentation.viewmodel.MyTabViewModel
import com.example.cinemalab.ui.filter.Filter
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class MyTabFragment : Fragment() {

    private lateinit var binding: FragmentMyTabBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var mapper: FilterMovieEntityMapper
    private lateinit var viewModel: MyTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyTabBinding.inflate(inflater, container, false)
        mapper = FilterMovieEntityMapper()
        val interestViewModel = ViewModelProvider(this)[InterestingViewModel::class.java]
        adapter =
            SearchAdapter(object : com.example.cinemalab.ui.search.adapter.MovieActionListener {
                override fun onMovie(movie: Doc) {
                    findNavController().navigate(
                        R.id.action_action_home_to_movieDetailFragment,
                        bundleOf(
                            MOVIE_ID to movie.id,
                            IN_FAVORITES to movie.inFavorites
                        )
                    )
                }

                override fun onFavorites(movie: Doc) {
                    if (!movie.inFavorites) {
                        movie.inFavorites = true
                        interestViewModel.insert(FavMovie(movie.id)){}
                    } else {
                        movie.inFavorites = false
                    }
                }

            })
        viewModel = ViewModelProvider(this)[MyTabViewModel::class.java]

        binding.setupButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_action_home_to_filterFragment,
                bundleOf(DESTINATION to MY_TAB)
            )
        }
        binding.filterButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_action_home_to_filterFragment,
                bundleOf(DESTINATION to MY_TAB)
            )
        }
        binding.myTabRecyclerView.adapter = adapter
        binding.myTabRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.onboardingGroup.visibility = View.VISIBLE
        binding.myTabReycler.visibility = View.GONE

        setUpMyTab()

        return binding.root
    }

    private fun setUpMyTab(){
        if (MY_TAB_OPTIONS != null) {
            val genres = arrayListOf<String>()
            MY_TAB_OPTIONS!!.genres.forEach {
                genres.add(it.genreName)
            }
            val countries = arrayListOf<String>()
            MY_TAB_OPTIONS!!.countries.forEach {
                countries.add(it.countryName)
            }
            val filter = Filter(
                typeNumber = MY_TAB_OPTIONS!!.typeNumber,
                genres = genres,
                country = countries,
                year = MY_TAB_OPTIONS!!.year,
                sortBy = MY_TAB_OPTIONS!!.sortField,
                rating = MY_TAB_OPTIONS!!.rating
            )
            viewModel.getFilterMovie(filter)
            viewModel.movie.observe(viewLifecycleOwner) {
                adapter.setList(mapper.mapFromModel(it).docs)
            }
            binding.onboardingGroup.visibility = View.GONE
            binding.myTabReycler.visibility = View.VISIBLE
        }
    }
}