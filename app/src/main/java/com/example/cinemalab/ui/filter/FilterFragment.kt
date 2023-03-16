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

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding

    private val optionsService = App.optionsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

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
        binding.yearSelect.setOnClickListener { findNavController().navigate(R.id.action_filterFragment_to_optionsFragment) }

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
            Toast.makeText(context,getFilterConfiguration().toString(),Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun getFilterConfiguration(): List<String>{

        val category = when(binding.categoriesRadioGroup.checkedRadioButtonId){
            binding.seriesRadio.id -> "Series"
            binding.filmsRadio.id -> "Films"
            else -> "All"
        }

        val sortBy = when(binding.sortByRadioGroup.checkedRadioButtonId){
            binding.byPopularityRadio.id -> "Popularity"
            binding.byDateRadio.id -> "Date"
            else -> "Rating"
        }

        val rating = binding.ratingText.text.toString()

        val genres = mutableListOf<String>()
        optionsService.getSelectedGenres().forEach {
            genres.add(it.genre)
        }

        val countries = mutableListOf<String>()
        optionsService.getSelectedCountries().forEach {
            countries.add(it.country)
        }

        return listOf(category,sortBy,rating,genres.toString(),countries.toString())
    }

}