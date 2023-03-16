package com.example.cinemalab.ui.favorites

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 6
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> FavPageDifferentOptFragment("Favorites")
            1 -> FavPageDifferentOptFragment("Watching")
            2 -> FavPageDifferentOptFragment("Scheduled")
            3 -> FavPageDifferentOptFragment("Viewed")
            4 -> FavPageDifferentOptFragment("Postponed")
            else -> FavPageDifferentOptFragment("Abandoned")
        }
    }
}