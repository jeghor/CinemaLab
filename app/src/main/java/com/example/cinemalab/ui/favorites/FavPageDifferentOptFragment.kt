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
import com.example.cinemalab.R
import com.example.cinemalab.data.remote.mapper.MovieEntityMapper
import com.example.cinemalab.data.remote.model.movie.Movie
import com.example.cinemalab.databinding.FragmentFavPageDifferentOptBinding
import com.example.cinemalab.ui.home.tabs.adapter.MyTabAdapter

class FavPageDifferentOptFragment(category: String) : Fragment() {

    private lateinit var binding: FragmentFavPageDifferentOptBinding
    private lateinit var adapter: MyTabAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavPageDifferentOptBinding.inflate(layoutInflater, container, false)
        val viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        adapter =
            MyTabAdapter(object : com.example.cinemalab.ui.home.tabs.adapter.MovieActionListener {
                override fun onMovie(movie: Movie) {
                    findNavController().navigate(
                        R.id.action_action_favorites_to_movieDetailFragment,
                        bundleOf(
                            "MOVIE ID" to movie.id,
                        )
                    )
                }

                override fun onFavorites(movie: Movie) {
                    if (!movie.inFavorites) {
                        movie.inFavorites = true
                        App.favoritesMovieIdsList.add(movie.id)
                    } else {
                        movie.inFavorites = false
                        App.favoritesMovieIdsList.remove(movie.id)
                    }
                }

            })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        val size = App.favoritesMovieIdsList.size
        val index = 0
        while (index < size) {
            viewModel.getMovieById(App.favoritesMovieIdsList[index])
            viewModel.movie.observe(viewLifecycleOwner) {
                adapter.addMovie(MovieEntityMapper().mapFromModel(it)!!)
            }
        }
        return binding.root
    }
}