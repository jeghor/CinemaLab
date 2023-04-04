package com.example.cinemalab.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cinemalab.PROFILE
import com.example.cinemalab.R
import com.example.cinemalab.databinding.FragmentProfileBinding
import com.example.cinemalab.presentation.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        val profileName = arguments?.getString("CHANGED_NAME") ?: resources.getString(R.string.app_name)

        binding.profileName.text = PROFILE?.name ?: resources.getString(R.string.app_name)

        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_profile_to_editProfileFragment)
        }
        binding.settingsProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_action_profile_to_settingsFragment)
        }

        return binding.root
    }
}