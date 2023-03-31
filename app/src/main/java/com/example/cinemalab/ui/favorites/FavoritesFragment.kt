package com.example.cinemalab.ui.favorites

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
import com.example.cinemalab.data.remote.mapper.MovieEntityMapper
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.databinding.FragmentFavoritesBinding
import com.example.cinemalab.ui.home.tabs.MovieActionListener
import com.example.cinemalab.ui.home.tabs.MovieAdapter

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        adapter = MovieAdapter(object : MovieActionListener {
            override fun onMovie(movie: Movie) {
                findNavController().navigate(
                    R.id.action_action_favorites_to_movieDetailFragment,
                    bundleOf(
                        "FILTER_MOVIE_ID" to movie.id,
                    )
                )
            }

            override fun onFavorites(movie: Movie) {
                movie.inFavorites = false
                App.favoritesMovieIdsList.remove(movie.id)
                adapter.removeMovie(movie)
            }

        })

        val viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_favorites_to_filterFragment)
        }
        binding.searchCustom.setOnClickListener { findNavController().navigate(R.id.action_action_favorites_to_searchEngineFragment) }

        App.favoritesMovieIdsList.forEach {
            viewModel.getMovieById(it)
        }
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            adapter.addMovie(MovieEntityMapper().mapFromModel(movie)!!)
        }

        binding.filterRecycler.setOnClickListener {
            Toast.makeText(context, adapter.itemCount.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        return binding.root
    }
}