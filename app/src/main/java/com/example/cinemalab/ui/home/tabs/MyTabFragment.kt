package com.example.cinemalab.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentMyTabBinding

class MyTabFragment : Fragment() {

    private lateinit var binding: FragmentMyTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyTabBinding.inflate(inflater, container,false)

        binding.setupButton.setOnClickListener { findNavController().navigate(R.id.action_action_home_to_filterFragment) }

        return binding.root
    }

}