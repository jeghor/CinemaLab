package com.example.cinemalab.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        init()

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_favorites_to_filterFragment)
        }
        binding.searchCustom.setOnClickListener { findNavController().navigate(R.id.action_action_favorites_to_searchEngineFragment) }

        return binding.root
    }

    private fun init() {
        binding.homeViewPager.adapter = FavPagerAdapter(this)
        binding.homeTabLayout.tabIconTint = null

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Favorites"
                1 -> tab.text = "Watching"
                2 -> tab.text = "Scheduled"
                3 -> tab.text = "Viewed"
                4 -> tab.text = "Postponed"
                else -> tab.text = "Abandoned"
            }
        }.attach()
    }

}