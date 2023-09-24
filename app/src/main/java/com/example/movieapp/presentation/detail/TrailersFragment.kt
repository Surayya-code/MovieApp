package com.example.movieapp.presentation.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.utils.gone
import com.example.movieapp.common.utils.visible
import com.example.movieapp.databinding.FragmentTrailersBinding
import com.example.movieapp.domein.state.TrailerUiState
import com.example.movieapp.presentation.detail.adapter.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrailersFragment(private val id: Int)
    : BaseFragment<FragmentTrailersBinding>(FragmentTrailersBinding::inflate) {

    private val viewModel by viewModels<TrailerViewModel>()
    private val trailerAdapter = TrailerAdapter()


    override fun onViewCreateFinish() {
        setRecycler()
        getData(id)
        observeEvents()
    }

    override fun observeEvents() {
        viewModel.trailer.observe(viewLifecycleOwner) {
            when (it) {
                is TrailerUiState.Success -> {
                    trailerAdapter.differ.submitList(it.data.results)
                    binding.progressBar4.gone()
                }

                is TrailerUiState.Error -> {
                    binding.progressBar4.gone()
                    binding.rvTrailer.gone()
                    binding.trailerEmpty.visible()
                    Toast.makeText(context, "Error trailers", Toast.LENGTH_SHORT).show()
                }

                is TrailerUiState.Loading -> {
                    binding.progressBar4.visible()
                }
            }
        }

    }

    private fun setRecycler() {
        binding.rvTrailer.adapter = trailerAdapter
    }

    private fun getData(id: Int) {
        viewModel.getTrailer(id)
    }

}