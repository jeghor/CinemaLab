package com.example.cinemalab.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container, false)

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_search_to_filterFragment)
        }
        binding.searchCustom.setOnClickListener { findNavController().navigate(R.id.action_action_search_to_searchEngineFragment) }
        return binding.root
    }
}