package com.example.cinemalab.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container,false)

        binding.backArrow.setOnClickListener { findNavController().popBackStack() }
        binding.saveChangesButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}