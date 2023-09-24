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

    override fun onViewCreateFinish() {

        binding.checkBoxRemember.setOnClickListener { view ->
            checkBoxClick(view)
        }

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

        binding.apply {
            signUpBtn.setOnClickListener {
                val user = User(
                    emailTextInput.text.toString().trim()
                )
                val password = passwordTextInput.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user,password)
                viewModel.saveEmail(user.email)
                viewModel.savePassword(password)
               findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            }
        }

        binding.loginText.setOnClickListener{
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }

    }



    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                with(binding){
                    when (it) {
                        is Resource.Success -> {
                          //  binding.signUpBtn.revertAnimation()
                            Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                        }

                        is Resource.Error -> {
                            Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                           // binding.signUpBtn.revertAnimation()
                        }

                        is Resource.Loading -> {
                           // binding.signUpBtn.startAnimation()
                        }
                        else ->Unit
                    }
                }
            }
        }
    }


    fun checkBoxClick(view: View) {
  if (view is MaterialCheckBox){
      val checked:Boolean=view.isChecked

      when(view.id){
          R.id.checkBoxRemember->{
              if(checked){
                  Snackbar.make(view,"Save Your Password", Snackbar.LENGTH_LONG).show()
              }else{
                  Snackbar.make(view,"We don't save Your Password", Snackbar.LENGTH_LONG).show()
              }
          }
      }
  }
    }






}





