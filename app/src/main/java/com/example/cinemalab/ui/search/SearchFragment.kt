package com.example.cinemalab.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.App
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.data.remote.model.filtermovie.FilterMovie
import com.example.cinemalab.databinding.FragmentSearchBinding
import com.example.cinemalab.ui.filter.Filter
import com.example.cinemalab.ui.search.adapter.MovieActionListener
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container, false)
        adapter = SearchAdapter(object : MovieActionListener{
            override fun onMovie(movie: Doc) {
                findNavController().navigate(
                    R.id.action_action_search_to_movieDetailFragment,
                    bundleOf("FILTER_MOVIE_ID" to movie.id)
                )
            }
            override fun onFavorites(movie: Doc) {
                movie.inFavorites = true
                App.favoritesMovieIdsList.add(movie.id)
            }

        })
        val viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        if (arguments?.isEmpty == false){
            val filter = Filter(
                typeNumber = arguments?.getInt("TYPE_NUMBER")!!,
                genres = arguments?.getStringArrayList("GENRES")!!,
                country = arguments?.getString("COUNTRY")!!,
                year = arguments?.getInt("YEAR")!!,
                sortBy = "Rating"
            )

            binding.searchRecyclerView.adapter = adapter
            binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)

            viewModel.getFilterMovie(filter)
            viewModel.movie.observe(viewLifecycleOwner){
                adapter.setList(FilterMovieEntityMapper().mapFromModel(it).docs)
                adapter.movieList.forEach {
                    App.filterMovieList.add(it)
                }
            }
        }

        binding.filterButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_action_search_to_filterFragment,
                bundleOf("DESTINATION" to "Search")
            )
        }
        binding.searchCustom.setOnClickListener { findNavController().navigate(R.id.action_action_search_to_searchEngineFragment) }
        return binding.root
    }
}