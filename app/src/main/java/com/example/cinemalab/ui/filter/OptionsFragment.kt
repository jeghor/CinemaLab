package com.example.cinemalab.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemalab.App
import com.example.cinemalab.OPTION_TYPE
import com.example.cinemalab.data.remote.mapper.OptionMapper
import com.example.cinemalab.data.remote.repository.Repository
import com.example.cinemalab.databinding.FragmentOptionsBinding
import com.example.cinemalab.domain.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    private lateinit var adapter: OptionAdapter
    private lateinit var type: String

    private val optionsService = App.optionsService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        type = arguments?.getString(OPTION_TYPE).toString()

        adapter = OptionAdapter(object : OptionActionListener{
            override fun onOption(option: Option) {
                option.isSelected = true
            }
        })

        binding.optionsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.optionsRecyclerView.adapter = adapter
        optionsService.addListener(optionListener)

        val query = if (type == "Genres"){
            "genres.name"
        } else "countries.name"

        CoroutineScope(Dispatchers.IO).launch {
            val repo = Repository()
            val mapper = OptionMapper()
            val response = repo.getPossibleValues(query)
            val options = mapper.mapFromModel(response)

            withContext(Dispatchers.Main){
                adapter.optionList = options
                if (type == "Genres"){
                    optionsService.genres.addAll(options)
                } else {
                    optionsService.countries.addAll(options)
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

}