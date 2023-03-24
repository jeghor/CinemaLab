package com.example.cinemalab.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init(requireContext())

        binding.searchView.setOnClickListener {
            findNavController().navigate(R.id.action_action_home_to_searchEngineFragment)
        }
        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_home_to_filterFragment)
        }

        return binding.root
    }

    private fun init(context: Context) {
        binding.homeViewPager.adapter = PagerAdapter(this)
        binding.homeTabLayout.tabIconTint = null

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = context.getString(R.string.my_tab)
                else -> tab.text = context.getString(R.string.interesting)
            }
        }.attach()
    }
}