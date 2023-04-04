package com.example.cinemalab.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.App
import com.example.cinemalab.MY_TAB_OPTIONS
import com.example.cinemalab.R
import com.example.cinemalab.data.cache.model.Profile
import com.example.cinemalab.databinding.FragmentEditProfileBinding
import com.example.cinemalab.presentation.viewmodel.ProfileViewModel

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container,false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.backArrow.setOnClickListener { findNavController().popBackStack() }
        binding.saveChangesButton.setOnClickListener {
            val changedName: String = binding.editProfileName.text.toString()
            findNavController().navigate(
                R.id.action_editProfileFragment_to_action_profile,
                bundleOf("CHANGED_NAME" to changedName)
            )

            viewModel.insert(
                Profile(
                name = changedName,
                photo = "",
                favMovies = App.favMovies,
                myTabOptions = MY_TAB_OPTIONS,
            )
            )
        }

        return binding.root
    }

}