package com.example.movieapp.presentation.mylList

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentMyListBinding
import com.example.movieapp.presentation.mylList.adapter.MyFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>(FragmentMyListBinding::inflate) {

    private val viewModel by viewModels<MyListViewModel>()
    private val favoriteAdapter = MyFavoriteAdapter()

    override fun onViewCreateFinish() {
        with(binding){
          favoriteAdapter.onDeleteClick={
              val alertDialog = AlertDialog.Builder(requireContext()).apply {
                  setTitle("Delete item from cart")
                  setMessage("Do you want to delete this item from your Favorite?")
                  setNegativeButton("Cancel") { dialog, _ ->
                      dialog.dismiss()
                  }
                  setPositiveButton("Delete") { dialog, _ ->
                      viewModel.deleteFavorite(it)
                      dialog.dismiss()
                  }

              }
              alertDialog.create()
              alertDialog.show()
          }
       }
    }

    override fun observeEvents() {
        viewModel.liveData.observe(viewLifecycleOwner){ result ->
            Log.d("MyListFragment", "LiveData changed: $result")
            when(result){
                is MyListViewModel.MyListUiState.Error -> Toast.makeText(requireContext(),result.message,Toast.LENGTH_SHORT).show()
                MyListViewModel.MyListUiState.Loading -> Unit
                is MyListViewModel.MyListUiState.MyListData -> Log.e("data",result.data.toString())
            }
        }
    }


}