package com.example.movieapp.presentation.mylList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.NetworkResponseState
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.FavoriteDTO
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
private val repository: movieRepo
): ViewModel() {

    private val _favorite = MutableLiveData<FavoriteUiState>()
    val favorite : LiveData<FavoriteUiState> get() = _favorite

    init {
        getFav()
    }

    private fun getFav(){
        viewModelScope.launch {
            repository.getFavorites().collectLatest {
                when(it){
                    is Resource.Success->{
                        _favorite.value = it.data?.let { it1 -> FavoriteUiState.Success(it1) }
                    }
                    is Resource.Error->{
                        _favorite.value = FavoriteUiState.Error("error fav")
                    }
                    is Resource.Loading->{
                        _favorite.value = FavoriteUiState.Loading
                    }
                }
            }
        }
    }

    fun deleteFav(product: FavoriteDTO){
        viewModelScope.launch {
            repository.deleteFav(product)
            getFav()
        }
    }
}