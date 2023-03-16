package com.example.cinemalab.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.databinding.FragmentSearchEngineBinding

class SearchEngineFragment : Fragment() {

    private lateinit var binding: FragmentSearchEngineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchEngineBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }
}