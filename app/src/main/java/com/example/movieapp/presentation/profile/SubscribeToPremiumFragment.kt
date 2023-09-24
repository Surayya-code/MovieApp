package com.example.movieapp.presentation.profile


import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentSubscribeToPremiumBinding


class SubscribeToPremiumFragment  : BaseFragment<FragmentSubscribeToPremiumBinding>(FragmentSubscribeToPremiumBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.constraintMonthly.setOnClickListener {
            findNavController().navigate(SubscribeToPremiumFragmentDirections.subscribeToPaymentFragment())

        }
        binding.constraintYearly.setOnClickListener {
            findNavController().navigate(SubscribeToPremiumFragmentDirections.subscribeToPaymentFragment())
        }
    }

    override fun observeEvents() {

    }

}