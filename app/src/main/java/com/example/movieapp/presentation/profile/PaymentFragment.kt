package com.example.movieapp.presentation.profile

import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentPaymentBinding


class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    override fun onViewCreateFinish() {
   binding.buttonAddCard.setOnClickListener {
      findNavController().navigate(PaymentFragmentDirections.paymentFragmentToAddNewCardFragment())

   }
    }

    override fun observeEvents() {

    }

}