package com.example.cinemalab.ui.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.App
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.FragmentLatestBinding
import com.example.cinemalab.ui.home.tabs.viewmodel.LatestViewModel
import com.example.cinemalab.ui.search.adapter.MovieActionListener
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class LatestFragment : Fragment() {

    private lateinit var binding: FragmentLatestBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestBinding.inflate(inflater, container,false)
        val viewModel = ViewModelProvider(this)[LatestViewModel::class.java]
        adapter = SearchAdapter(object : MovieActionListener{
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
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.getLatestMovies()
        viewModel.movie.observe(viewLifecycleOwner){
            adapter.setList(FilterMovieEntityMapper().mapFromModel(it).docs)
        }

        return binding.root
    }
}