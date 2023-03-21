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
import com.example.cinemalab.databinding.FragmentInterestingBinding
import com.example.cinemalab.model.FactsService
import com.example.cinemalab.ui.home.tabs.viewmodel.InterestingViewModel
import com.example.cinemalab.ui.search.adapter.MovieActionListener
import com.example.cinemalab.ui.search.adapter.SearchAdapter

class InterestingFragment : Fragment() {

    private lateinit var binding: FragmentInterestingBinding
    private lateinit var adapter: SearchAdapter

    private val factsService: FactsService = App.factsService

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

        viewModel.getMovie()
        viewModel.movie.observe(viewLifecycleOwner){
            adapter.setList(FilterMovieEntityMapper().mapFromModel(it).docs)
        }

        //factsService.addListener(factsListener)

        return binding.root
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        factsService.removeListener(factsListener)
//    }
//
//    private val factsListener: FactsListener = {
//        adapter.facts = it
//    }

}