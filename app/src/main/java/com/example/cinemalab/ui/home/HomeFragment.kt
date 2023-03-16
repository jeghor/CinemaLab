package com.example.cinemalab.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemalab.databinding.FragmentHomeBinding
import com.example.cinemalab.ui.home.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        binding.homeViewPager.adapter = PagerAdapter(this)
        binding.homeTabLayout.tabIconTint = null

        TabLayoutMediator(binding.homeTabLayout,binding.homeViewPager){ tab, position ->
            when(position){
                0 -> tab.text = "My Tab"
                1 -> tab.text = "Interesting"
                else -> tab.text = "Latest"
            }
        }.attach()
    }
}