package com.example.cinemalab.ui.search

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
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.FragmentSearchBinding
import com.example.cinemalab.presentation.viewmodel.InterestingViewModel
import com.example.cinemalab.ui.search.adapter.MovieActionListener
import com.example.cinemalab.ui.search.adapter.SearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = SearchAdapter(object : MovieActionListener {
            override fun onMovie(movie: Doc) {
                findNavController().navigate(
                    R.id.action_searchFragment_to_movieDetailFragment,
                    bundleOf("FILTER_MOVIE_ID" to movie.id)
                )
            }

            override fun onFavorites(movie: Doc) {
                movie.inFavorites = true
                App.favoritesMovieIdsList.add(movie.id)
            }

        })
        val viewModel = ViewModelProvider(this)[InterestingViewModel::class.java]

        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)

        val typeNumber = arguments?.getInt(TYPE_NUMBER)
        val genres = arguments?.getStringArrayList(GENRES)
        val countries = arguments?.getStringArrayList(COUNTRY)
        val year = arguments?.getString(YEAR)
        val rating = arguments?.getString(RATING)
        val sortField = arguments?.getString(SORT_BY)

        viewModel.getFilterMovie(typeNumber!!,genres!!,countries!!,year!!,rating!!,sortField!!)

        viewModel.movie.observe(viewLifecycleOwner){
            adapter.setList(FilterMovieEntityMapper().mapFromModel(it).docs)
        }

        CoroutineScope(Dispatchers.Main).launch {
            if (adapter.itemCount == 0){
                binding.searchRecyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

                delay(1500)
                binding.progressBar.visibility = View.GONE
                binding.searchRecyclerView.visibility = View.VISIBLE
            }
        }

        return binding.root
    }
}