package com.example.movieapp.presentation.seeAllMovies

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.utils.MovieTypeEnum
import com.example.movieapp.common.utils.gone
import com.example.movieapp.databinding.FragmentSeeAllMoviesBinding
import com.example.movieapp.domein.state.MovieUiState
import com.example.movieapp.presentation.seeAllMovies.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllMoviesFragment : BaseFragment<FragmentSeeAllMoviesBinding>(FragmentSeeAllMoviesBinding::inflate) {


    private  val args: SeeAllMoviesFragmentArgs by navArgs()
    private val viewModel: SeeAllMoviesViewModel by viewModels()
    private val adapter = MovieListAdapter()

    override fun onViewCreateFinish() {
        setRecyclerView()
        val movieType = args.movie
        binding.textViewTitle.text = movieType.title
        when (movieType.type) {
            MovieTypeEnum.TOP_10_MOVIES -> viewModel.getTop10()
            MovieTypeEnum.NEW_RELEASE_MOVIES -> viewModel.getNewRelease()
        }
    }
    override fun observeEvents() {
        with(binding) {
            with(viewModel) {
                movieData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            progressBarloading.gone()
                            adapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            progressBarloading.gone()
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }

                        is MovieUiState.Loading -> {
                            progressBarloading.visibility
                        }
                    }

                }
            }
        }



    }
    private fun setRecyclerView() {
        binding.rvMovie.adapter = adapter
    }
}