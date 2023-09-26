package com.example.movieapp.presentation.auth

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.common.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.utils.RegisterValidation
import com.example.movieapp.common.utils.User
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.common.utils.setupBottomSheetDialog
import com.example.movieapp.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    private val viewModel by viewModels<SigInMVVM>()
    var isChecked = false
    override fun onViewCreateFinish() {
        onBackPress()
        checkClick()
        binding.signUpText.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections
                           .actionSignInFragmentToSignUpFragment())
        }

 //Email and Password
        lifecycleScope.launch {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.emailTextInput.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                } else if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.passwordTextInput.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }

//SignIn
        binding.apply {
            signInBtn.setOnClickListener {
                val user = User(
                    emailTextInput.text.toString().trim()
                )
                val password = passwordTextInput.text.toString().trim()

                viewModel.loginUser(user, password)
            }
        }

//forgotText
        binding.textForgot.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }
    }
///checkBox
      private fun checkClick(){
        binding.checkBoxRemember.setOnClickListener {
            val imageRes = if(isChecked){
                R.drawable.check_box_outline
            }else{
                R.drawable.check_box
            }
            binding.checkBoxRemember.setButtonIconDrawableResource(imageRes)
            isChecked = !isChecked
        }
    }
    private fun onBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
              // findNavController().navigate(SignInFragmentDirections.tosplash())
            }
        })
    }

    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        findNavController().navigate(SignInFragmentDirections.toHome())
                    }
                    is Resource.Error -> {
                        Toast.makeText(context,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            resetPassword.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        Snackbar.make(
                            requireView(),
                            "Reset link was sent to your email address",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    is Resource.Error -> {
                        Snackbar.make(requireView(), "Error ${it.exception}", Snackbar.LENGTH_LONG)
                            .show()
                    }

                }
            }

        }
    }


}