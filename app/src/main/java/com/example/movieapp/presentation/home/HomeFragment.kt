package com.example.movieapp.presentation.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.utils.gone
import com.example.movieapp.common.utils.MovieTypeEnum
import com.example.movieapp.common.utils.showToast
import com.example.movieapp.common.utils.visible
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.domein.MovieType
import com.example.movieapp.domein.state.MovieUiState
import com.example.movieapp.presentation.home.adapter.NewReleaseAdapter
import com.example.movieapp.presentation.home.adapter.PagerAdapter
import com.example.movieapp.presentation.home.adapter.Top10Adapter
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val top10Adapter = Top10Adapter()
    private val newReleaseAdapter = NewReleaseAdapter()
    private val viewPagerAdapter = PagerAdapter()

    override fun onViewCreateFinish() {
        binding.textViewSeeAllOne.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllMoviesFragment(
                MovieType("Top 10 Movies This Week", MovieTypeEnum.TOP_10_MOVIES)
            ))
        }
        binding.textViewSeeAllTwo.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllMoviesFragment(
                MovieType("NewReleases", MovieTypeEnum.NEW_RELEASE_MOVIES)
            ))

        }

        setRecyclerView()
    }

    override fun observeEvents() {

        with(viewModel) {

            topRatedMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is MovieUiState.Success -> {
                        top10Adapter.differ.submitList(it.data.subList(0, 10))
                        binding.progressBarRv.gone()
                    }

                    is MovieUiState.Error -> {
                        requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                        binding.progressBarRv.gone()
                    }

                    is MovieUiState.Loading -> {
                        binding.progressBarRv.visible()
                    }

                    else -> Unit
                }
            }
            nowPlayingMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is MovieUiState.Success -> {

                        viewPagerAdapter.differ.submitList(it.data)
                        binding.progressBarRv.gone()
                    }

                    is MovieUiState.Error -> {
                        requireActivity().showToast("Error404", it.message, MotionToastStyle.ERROR)
                        binding.progressBarRv.gone()
                    }

                    is MovieUiState.Loading -> {
                        binding.progressBarRv.visible()
                    }

                    else -> Unit
                }
            }

            upComingMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is MovieUiState.Success -> {
                        newReleaseAdapter.differ.submitList(it.data)
                    }

                    is MovieUiState.Error -> {
                        requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                    }

                    is MovieUiState.Loading -> {}
                }
            }

        }
    }


    private fun setRecyclerView() {
        with(binding) {
            rvTopMovies.adapter = top10Adapter
            rvNewRelease.adapter= newReleaseAdapter
            viewPager.adapter= viewPagerAdapter

        }
    }


}
