package com.example.cinemalab.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.App
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentInterestingBinding
import com.example.cinemalab.model.FactsListener
import com.example.cinemalab.model.FactsService
import com.example.cinemalab.ui.home.adapter.FactsAdapter

class InterestingFragment : Fragment() {

    private lateinit var binding: FragmentInterestingBinding
    private lateinit var adapter: FactsAdapter

    private val factsService: FactsService = App.factsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterestingBinding.inflate(inflater, container,false)
        adapter = FactsAdapter()

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        factsService.addListener(factsListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        factsService.removeListener(factsListener)
    }

    private val factsListener: FactsListener = {
        adapter.facts = it
    }

}