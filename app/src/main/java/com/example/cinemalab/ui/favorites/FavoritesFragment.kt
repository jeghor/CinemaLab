package com.example.cinemalab.ui.favorites

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
import com.example.cinemalab.IN_FAVORITES
import com.example.cinemalab.MOVIE_ID
import com.example.cinemalab.R
import com.example.cinemalab.data.cache.model.FavMovie
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
        val viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        adapter = MovieAdapter(object : MovieActionListener {
            override fun onMovie(movie: Movie) {
                findNavController().navigate(
                    R.id.action_action_favorites_to_movieDetailFragment,
                    bundleOf(
                        MOVIE_ID to movie.id,
                        IN_FAVORITES to true
                    )
                )
            }

            override fun onFavorites(movie: Movie) {
                movie.inFavorites = false
                App.favMovies.remove(FavMovie(movie.id))
                viewModel.delete(FavMovie(movie.id)){}
                adapter.removeMovie(movie)
            }

        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_favorites_to_filterFragment)
        }
        binding.searchCustom.setOnClickListener { findNavController().navigate(R.id.action_action_favorites_to_searchEngineFragment) }

        App.favMovies.forEach {
            viewModel.getMovieById(it.id)
        }
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            adapter.addMovie(MovieEntityMapper().mapFromModel(movie)!!)
        }
        binding.amount.text = "${App.favMovies.size} ${resources.getString(R.string.total_in_favorites)}"

        return binding.root
    }
}