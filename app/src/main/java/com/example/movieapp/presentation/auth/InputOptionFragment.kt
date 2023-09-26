package com.example.movieapp.presentation.auth

import com.example.movieapp.common.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentInputOptionBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InputOptionFragment : BaseFragment<FragmentInputOptionBinding>(FragmentInputOptionBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.btnSignin.setOnClickListener {
            findNavController().navigate(R.id.action_inputOptionFragment_to_signInFragment)
        }
        binding.singUpText.setOnClickListener(){
            findNavController().navigate(R.id.action_inputOptionFragment_to_signUpFragment)
        }
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeEvents() {

    }


}