package com.example.movieapp.presentation.splash


import com.example.movieapp.common.base.BaseFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel by viewModels<SplashMVM>()
    override fun onViewCreateFinish() {
        binding.apply {
            lottie.alpha = 0f
            lottie.animate().setDuration(2500).alpha(1f).start()
        }
    }

    override fun observeEvents() {
            viewModel.auth.observe(viewLifecycleOwner){
                if(it){
                    findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)

                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }
    }


}

