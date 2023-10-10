package com.example.movieapp.presentation.explore

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import androidx.appcompat.widget.SearchView
import com.example.movieapp.common.utils.gone
import com.example.movieapp.common.utils.showToast
import com.example.movieapp.common.utils.visible
import com.example.movieapp.databinding.FragmentExploreBinding
import com.example.movieapp.domein.state.MovieUiState
import com.example.movieapp.presentation.explore.adapter.ExploreAdapter
import com.example.movieapp.presentation.explore.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class ExploreFragment
    : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private val viewModel : ExploreMVVM by viewModels()
    private val exploreAdapter = ExploreAdapter()
    private val searchAdapter = SearchAdapter()

    override fun onViewCreateFinish() {
        setAdapters()

        binding.filter.setOnClickListener {
            findNavController().navigate(ExploreFragmentDirections.tofilter())
        }

        with(binding){
            editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let{
                        viewModel.getSearch(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText?.length == 0){
                        rvExplore.visible()
                        lySearch.gone()
                        notFoundPage.gone()
                        false
                    }else{
                        newText?.let{
                            viewModel.getSearch(it)
                        }
                        rvExplore.gone()
                        notFoundPage.gone()
                        lySearch.visible()
                        true
                    }
                }
            })
        }

    }

    override fun observeEvents() {
        with(viewModel){
            with(binding){
                exploreData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loadingExplore.gone()
                            notFoundPage.gone()
                            exploreAdapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            loadingExplore.gone()
                            notFoundPage.visible()

                        }

                        is MovieUiState.Loading -> {
                            loadingExplore.visible()
                        }

                        else->Unit
                    }
                }


                searchData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loadingExplore.gone()
                            if (it.data.isEmpty()) {
                                notFoundPage.visible()

                            }else{
                                notFoundPage.gone()
                            }
                            searchAdapter.differ.submitList(it.data)

                        }
                        is MovieUiState.Error -> {
                            loadingExplore.gone()
                            requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                            notFoundPage.visible()

                        }

                        is MovieUiState.Loading -> {
                            loadingExplore.visible()
                        }

                        else->Unit
                    }
                }
            }

        }
    }

    private fun setAdapters() {
        with(binding) {
            rvExplore.adapter = exploreAdapter
            rvSearch.adapter = searchAdapter
        }
    }

}
