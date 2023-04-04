package com.example.cinemalab.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.*
import com.example.cinemalab.data.cache.model.MyTabOptions
import com.example.cinemalab.databinding.FragmentHomeBinding
import com.example.cinemalab.domain.model.Option
import com.example.cinemalab.presentation.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        with(viewModel) {
            initDatabase()
            getFavMovies().observe(viewLifecycleOwner) {
                App.favMovies.clear()
                App.favMovies.addAll(it.asReversed())
            }
            getGenres().observe(viewLifecycleOwner) {
                it.forEach { genre ->
                    GENRES_LIST.add(Option(genre.genreName))
                }
            }
            getCountries().observe(viewLifecycleOwner) {
                it.forEach { country ->
                    COUNTRIES_LIST.add(Option(country.countryName))
                }
            }
            getMyTabOpt().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val opt = it[0]
                    MY_TAB_OPTIONS = MyTabOptions(
                        opt.typeNumber,
                        opt.genres,
                        opt.countries,
                        opt.year,
                        opt.rating,
                        opt.sortField,
                    )
                }
            }
            getProfile().observe(viewLifecycleOwner){
                PROFILE = it
            }
        }

    }
}