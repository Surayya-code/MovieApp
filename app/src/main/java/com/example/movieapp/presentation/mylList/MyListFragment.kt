package com.example.movieapp.presentation.mylList
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.utils.gone
import com.example.movieapp.common.utils.visible
import com.example.movieapp.databinding.FragmentMyListBinding
import com.example.movieapp.domein.state.FavoriteUiState
import com.example.movieapp.presentation.mylList.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>(FragmentMyListBinding::inflate) {

    private val favoriteAdapter = FavoriteAdapter()
    private val viewModel : MyListViewModel by viewModels()


    override fun onViewCreateFinish() {
        setRecyclerView()
        setButtons()
    }

    override fun observeEvents() {
        viewModel.favorite.observe(viewLifecycleOwner){
            with(binding){
                when (it) {
                    is FavoriteUiState.Success -> {
                        if (it.data.isEmpty()) {
                            emptyList.visible()
                        } else {
                            emptyList.gone()
                        }
                        favoriteAdapter.differ.submitList(it.data)

                    }
                    is FavoriteUiState.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is FavoriteUiState.Loading -> {
                    }
                }
            }
        }
    }
    private fun setRecyclerView(){
        with(binding){
            rvFavorite.adapter =favoriteAdapter
        }
    }

    private fun setButtons(){
        with(binding){
            favoriteAdapter.onDelete={
                viewModel.deleteFav(it)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
            favoriteAdapter.onClick={
                findNavController().navigate(MyListFragmentDirections.actionMyListFragmentToMovieDetailsFragment(it.toString()))

            }
        }
    }

    private fun hideEmptyCart() {
        binding.emptyList.gone()
    }

    private fun showEmptyCart() {
        binding.emptyList.visible()
    }


}