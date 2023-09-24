package com.example.movieapp.presentation.welcome

import com.example.movieapp.common.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentWelcomeBinding


class WelcomeFragment :  BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.getStartedBtn.setOnClickListener(){
            findNavController().navigate(R.id.action_welcomeFragment_to_inputOptionFragment)
        }
    }

    override fun observeEvents() {

    }

}
