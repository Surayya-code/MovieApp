package com.example.movieapp.presentation.explore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }
    private fun setListener() {
        with(binding) {
            btnApply.setOnClickListener { dismiss() }
            btnReset.setOnClickListener { clearChip() }
        }
    }

    private fun clearChip() {
        binding.apply {
            cgCategories.clearCheck()
            cgGenre.clearCheck()
            cgRegion.clearCheck()
            cgSort.clearCheck()
            cgTime.clearCheck()
        }
    }
}