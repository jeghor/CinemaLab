package com.example.cinemalab.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cinemalab.ui.home.tabs.InterestingFragment
import com.example.cinemalab.ui.home.tabs.MyTabFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> MyTabFragment()
            else -> InterestingFragment()
        }
    }
}