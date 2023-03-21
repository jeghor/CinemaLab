package com.example.cinemalab.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.App
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentFilterBinding
import kotlinx.android.synthetic.main.fragment_filter.view.*

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    private val optionsService = App.optionsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        val fromFragment = arguments?.getString("DESTINATION")

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.clearButton.setOnClickListener{
            Toast.makeText(requireContext(), "Clear", Toast.LENGTH_SHORT).show()
        }

        binding.genresSelect.setOnClickListener { findNavController().navigate(R.id.action_filterFragment_to_optionsFragment,
            bundleOf("TYPE" to "Genres")
        ) }
        binding.countrySelect.setOnClickListener { findNavController().navigate(R.id.action_filterFragment_to_optionsFragment,
            bundleOf("TYPE" to "Countries")
        ) }
        binding.yearSelect.setOnClickListener {
            it.yearEdit.callOnClick()
        }
        binding.yearEdit.setOnClickListener {
            val year = binding.yearEdit.text.toString()
            binding.yearEdit.setText(year)
        }

        binding.ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.ratingText.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        binding.searchButton.setOnClickListener {
            when (fromFragment){
                "MyTab" -> {
                    findNavController().navigate(R.id.myTabFragment, bundleOf(
                        "TYPE_NUMBER" to getCategory(),
                        "GENRES" to getGenres(),
                        "COUNTRY" to getCountry(),
                        "YEAR" to getYear(),
                    )
                    )
                }
                "Search" -> {
                    findNavController().navigate(R.id.action_search, bundleOf(
                        "TYPE_NUMBER" to getCategory(),
                        "GENRES" to getGenres(),
                        "COUNTRY" to getCountry(),
                        "YEAR" to getYear(),
                    )
                    )
                }
            }

        }

        return binding.root
    }

    private fun getCategory():Int{
        val category = when(binding.categoriesRadioGroup.checkedRadioButtonId){
            binding.seriesRadio.id -> 2
            binding.filmsRadio.id -> 1
            else -> 1
        }
        return category
    }

    private fun getGenres():ArrayList<String>{
        val genres = arrayListOf<String>()
        optionsService.getSelectedGenres().forEach {
            genres.add(it.genre)
        }
        return genres
    }

    private fun getCountry():String{
        val countries = mutableListOf<String>()
        optionsService.getSelectedCountries().forEach {
            countries.add(it.country)
        }
        return countries[0]
    }

    private fun getYear():Int{
        var year = 2023
        if (binding.yearEdit.text.isNotBlank()){
            year = binding.yearEdit.text.toString().toInt()
        }
        return year
    }

}