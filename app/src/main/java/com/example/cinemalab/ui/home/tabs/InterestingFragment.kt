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
import com.example.cinemalab.data.remote.mapper.FilterMovieEntityMapper
import com.example.cinemalab.data.remote.model.filtermovie.Doc
import com.example.cinemalab.databinding.FragmentInterestingBinding
import com.example.cinemalab.presentation.viewmodel.InterestingViewModel
import com.example.cinemalab.ui.search.adapter.MovieActionListener
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class InterestingFragment : Fragment() {

    private lateinit var binding: FragmentInterestingBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterestingBinding.inflate(inflater, container,false)
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
        val viewModel = ViewModelProvider(this)[InterestingViewModel::class.java]

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

}