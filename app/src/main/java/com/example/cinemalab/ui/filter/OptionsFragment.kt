package com.example.cinemalab.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.App
import com.example.cinemalab.databinding.FragmentOptionsBinding
import com.example.cinemalab.model.Country
import com.example.cinemalab.model.CountryListener
import com.example.cinemalab.model.Genre
import com.example.cinemalab.model.GenresListener
import com.example.cinemalab.ui.filter.adapter.CountriesAdapter
import com.example.cinemalab.ui.filter.adapter.CountryActionListener
import com.example.cinemalab.ui.filter.adapter.GenreActionListener
import com.example.cinemalab.ui.filter.adapter.GenresAdapter

class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var type: String

    private val optionsService = App.optionsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        type = arguments?.getString("TYPE").toString()


        genresAdapter = GenresAdapter(object : GenreActionListener{
            override fun onGenre(genre: Genre) {
                optionsService.selectGenre(genre)
            }

        })
        countriesAdapter = CountriesAdapter(object : CountryActionListener{
            override fun onCountry(country: Country) {
                optionsService.selectCountry(country)
            }

        })

        val layoutManager = LinearLayoutManager(context)
        binding.optionsRecyclerView.layoutManager = layoutManager
        if (type == "Genres"){
            binding.optionsRecyclerView.adapter = genresAdapter
        } else binding.optionsRecyclerView.adapter = countriesAdapter



        addListener(type)

        binding.backArrow.setOnClickListener { findNavController().popBackStack() }
        binding.clearButton.setOnClickListener {
            if (type == "Genres"){
                optionsService.clearGenresOptions()
            } else optionsService.clearCountriesOptions()
            Toast.makeText(context, "Clear $type options", Toast.LENGTH_SHORT ).show()
        }
        binding.doneButton.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        removeListener(type)
    }

    private fun addListener(type:String){
        if (type=="Genres"){
            optionsService.addGenresListener(genresListener)
        } else {
            optionsService.addCountryListener(countryListener)
        }
    }

    private fun removeListener(type: String){
        if (type=="Genres"){
            optionsService.removeGenresListener(genresListener)
        } else {
            optionsService.removeCountryListener(countryListener)
        }
    }

    private val genresListener: GenresListener = {
        genresAdapter.genresList = it
    }

    private val countryListener: CountryListener = {
        countriesAdapter.countriesList = it
    }

}