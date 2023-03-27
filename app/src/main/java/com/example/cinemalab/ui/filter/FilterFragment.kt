package com.example.cinemalab.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.*
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
        setOptionsText()

        val fromFragment = arguments?.getString(DESTINATION)

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.clearButton.setOnClickListener {
            clearOptions()
        }

        binding.genresSelect.setOnClickListener {
            findNavController().navigate(
                R.id.action_filterFragment_to_optionsFragment,
                bundleOf(OPTION_TYPE to "Genres")
            )
        }
        binding.countrySelect.setOnClickListener {
            findNavController().navigate(
                R.id.action_filterFragment_to_optionsFragment,
                bundleOf(OPTION_TYPE to "Countries")
            )
        }
        binding.yearSelect.setOnClickListener {
            it.yearEdit.callOnClick()
        }
        binding.yearEdit.setOnClickListener {
            val year = binding.yearEdit.text.toString()
            binding.yearEdit.setText(year)
        }

        binding.ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.ratingText.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        binding.searchButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_filterFragment_to_searchFragment, bundleOf(
                    TYPE_NUMBER to getCategory(),
                    GENRES to getGenres(),
                    COUNTRY to getCountry(),
                    YEAR to getYear(),
                    RATING to getRating(),
                    SORT_BY to getSort()
                )
            )
        }

        return binding.root
    }

    private fun getCategory(): Int {
        val category = when (binding.categoriesRadioGroup.checkedRadioButtonId) {
            binding.seriesRadio.id -> 2
            binding.filmsRadio.id -> 1
            else -> 3
        }
        return category
    }

    private fun getGenres(): ArrayList<String> {
        val genres = arrayListOf<String>()
        optionsService.genres.forEach {
            if (it.isSelected) {
                genres.add(it.name)
            }
        }
        return genres
    }

    private fun getCountry(): ArrayList<String> {
        val countries = ArrayList<String>()
        optionsService.countries.forEach {
            if (it.isSelected) {
                countries.add(it.name)
            }
        }
        return countries
    }

    private fun getYear(): String {
        var year = "1990-2023"
        if (binding.yearEdit.text.isNotBlank()) {
            year = binding.yearEdit.text.toString().trim()
        }
        return year
    }

    private fun getRating(): String = "${binding.ratingText.text}-10"

    private fun getSort(): String {
        val sort = when (binding.sortByRadioGroup.checkedRadioButtonId) {
            binding.byDateRadio.id -> "year"
            binding.byPopularityRadio.id -> "votes.kp"
            else -> "rating.kp"
        }
        return sort
    }

    private fun setOptionsText(){
        val genres = getGenres()
        val countries = getCountry()

        if (genres.size!=0){
            binding.genresDetail.text = genres[0]
        }
        if (countries.size!=0){
            binding.countriesDetail.text = countries[0]
        }
    }

    private fun clearOptions(){

    }

}