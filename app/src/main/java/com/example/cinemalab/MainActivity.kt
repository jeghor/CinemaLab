package com.example.cinemalab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cinemalab.databinding.ActivityMainBinding
import com.example.cinemalab.ui.favorites.FavoritesFragment
import com.example.cinemalab.ui.home.HomeFragment
import com.example.cinemalab.ui.profile.ProfileFragment
import com.example.cinemalab.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {setContentView(it.root)}

        replaceFragment(HomeFragment())
        val navController = findNavController(R.id.nav_host_container)

        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.itemIconTintList = null
        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> navController.navigate(R.id.action_home)
                R.id.action_search -> navController.navigate(R.id.action_search)//replaceFragment(SearchFragment())
                R.id.action_favorites -> navController.navigate(R.id.action_favorites)
                R.id.action_profile -> navController.navigate(R.id.action_profile)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container,fragment)
            .commit()
    }
}