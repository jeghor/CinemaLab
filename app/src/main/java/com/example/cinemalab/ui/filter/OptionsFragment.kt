package com.example.cinemalab.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.*
import com.example.cinemalab.data.cache.model.Country
import com.example.cinemalab.data.cache.model.Genre
import com.example.cinemalab.data.remote.mapper.OptionMapper
import com.example.cinemalab.databinding.FragmentOptionsBinding
import com.example.cinemalab.domain.model.*
import com.example.cinemalab.presentation.viewmodel.OptionsViewModel

class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    private lateinit var adapter: OptionAdapter
    private lateinit var viewModel: OptionsViewModel
    private lateinit var mapper: OptionMapper

    private val optionsService = App.optionsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        mapper = OptionMapper()
        viewModel = ViewModelProvider(this)[OptionsViewModel::class.java]

        val type = arguments?.getString(OPTION_TYPE).toString()

        adapter = OptionAdapter(object : OptionActionListener {
            override fun onOption(option: Option) {
                option.isSelected = true
            }
        })

        binding.optionsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.optionsRecyclerView.adapter = adapter
        optionsService.addListener(optionListener)

        when (type) {
            GENRES -> {
                if (GENRES_LIST.isEmpty()) {
                    getOptionsByType(type)

                } else {
                    adapter.optionList = GENRES_LIST
                    optionsService.genres.addAll(GENRES_LIST)
                }
            }
            COUNTRY -> {
                if (COUNTRIES_LIST.isEmpty()) {
                    getOptionsByType(type)
                } else {
                    adapter.optionList = COUNTRIES_LIST
                    optionsService.genres.addAll(COUNTRIES_LIST)
                }
            }
        }
        binding.backArrow.setOnClickListener { findNavController().popBackStack() }
        binding.doneButton.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        optionsService.removeListener(optionListener)
    }

    private val optionListener: OptionListener = {
        adapter.optionList = it
    }

    private fun getOptionsByType(type: String) {
        viewModel.getOptions(type)
        viewModel.option.observe(viewLifecycleOwner) {
            val optionsList = mapper.mapFromModel(it)
            adapter.optionList = optionsList
            if (type == GENRES) {
                optionsService.genres.addAll(optionsList)
                optionsList.forEach { genre ->
                    viewModel.insertGenre(Genre(genre.name))
                }
            } else {
                optionsService.countries.addAll(optionsList)
                optionsList.forEach { country ->
                    viewModel.insertCountry(Country(country.name))
                }
            }
        }
    }

}