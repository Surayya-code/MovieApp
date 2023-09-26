package com.example.movieapp.presentation.auth

import com.example.movieapp.common.base.BaseFragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.utils.RegisterValidation
import com.example.movieapp.common.utils.User
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.databinding.FragmentSignUpBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel by viewModels<SignUpMVVM>()
    var isChecked = false
    override fun onViewCreateFinish() {
        checkBox()
        //validations
        lifecycleScope.launch {
            viewModel.validation.collect{validation ->
                if (validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.emailTextInput.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }else if (validation.password is RegisterValidation.Failed){
                    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                        binding.passwordTextInput.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }
///Create User and password
        binding.apply {
            signUpBtn.setOnClickListener {
                val user = User(
                    emailTextInput.text.toString().trim()
                )
                val password = passwordTextInput.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user,password)
                viewModel.saveEmail(user.email)
                viewModel.savePassword(password)

            }
        }

        binding.loginText.setOnClickListener{
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }

    }


///Create Account
    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                with(binding){
                    when (it) {
                        is Resource.Success -> {
                            Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                        }

                        is Resource.Error -> {
                            Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                        }

                        is Resource.Loading -> {
                        }
                        else ->Unit
                    }
                }
            }
        }
    }


    //CheckClick
  fun checkBox(){
        binding.checkBoxRemember.setOnClickListener { view ->
            val imageRes = if(isChecked){
                R.drawable.check_box_outline
            }else{
                R.drawable.check_box
            }
            binding.checkBoxRemember.setButtonIconDrawableResource(imageRes)
            //  binding.checkBoxRemember.setImageResource(imageRes)
            isChecked = !isChecked
        }
  }






}





