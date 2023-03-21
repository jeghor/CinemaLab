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
import com.example.cinemalab.App
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.FragmentMyTabBinding
import com.example.cinemalab.ui.filter.Filter
import com.example.cinemalab.ui.home.tabs.viewmodel.MyTabViewModel
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class MyTabFragment() : Fragment() {

    private lateinit var binding: FragmentMyTabBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyTabBinding.inflate(inflater, container, false)
        adapter =
            SearchAdapter(object : com.example.cinemalab.ui.search.adapter.MovieActionListener {
                override fun onMovie(movie: Doc) {
                    findNavController().navigate(
                        R.id.action_action_home_to_movieDetailFragment,
                        bundleOf(
                            "MOVIE ID" to movie.id,
                        )
                    )
                }

                override fun onFavorites(movie: Doc) {
                    if (!movie.inFavorites){
                        movie.inFavorites = true
                        App.favoritesMovieIdsList.add(movie.id)
                    } else {
                        movie.inFavorites = false
                        App.favoritesMovieIdsList.remove(movie.id)
                    }
                }

            })
        val viewModel = ViewModelProvider(this)[MyTabViewModel::class.java]

        binding.setupButton.setOnClickListener { findNavController().navigate(
            R.id.action_action_home_to_filterFragment,
            bundleOf("DESTINATION" to "MyTab")
        ) }
        binding.myTabRecyclerView.adapter = adapter
        binding.myTabRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.onboardingGroup.visibility = View.VISIBLE
        binding.myTabRecyclerView.visibility = View.GONE

        if (arguments != null){
            binding.onboardingGroup.visibility = View.GONE
            binding.myTabRecyclerView.visibility = View.VISIBLE
            val filter = Filter(
                typeNumber = arguments?.getInt("TYPE_NUMBER") ?: 1,
                genres = arguments?.getStringArrayList("GENRES")!!,
                country = arguments?.getString("COUNTRY")!!,
                year = arguments?.getInt("YEAR") ?: 2020,
                sortBy = "Rating"
            )

            binding.myTabRecyclerView.adapter = adapter
            binding.myTabRecyclerView.layoutManager = LinearLayoutManager(context)

            viewModel.getFilterMovie(filter)
            viewModel.movie.observe(viewLifecycleOwner){
                adapter.setList(FilterMovieEntityMapper().mapFromModel(it).docs)
                adapter.movieList.forEach {
                    App.filterMovieList.add(it)
                }
            }
        }

        return binding.root
    }
}