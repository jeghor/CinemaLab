package com.example.cinemalab.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater,container,false)

        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_profile_to_editProfileFragment)
        }
        binding.settingsProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_profile_to_settingsFragment)
        }

        return binding.root
    }
}