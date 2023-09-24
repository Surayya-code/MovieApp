package com.example.movieapp.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreMVVM @Inject constructor(
    val repository: movieRepo
): ViewModel() {


    private val _searchData = MutableLiveData<MovieUiState>()
    val searchData: LiveData<MovieUiState> get() = _searchData


    private val _exploreData = MutableLiveData<MovieUiState>()
    val exploreData: LiveData<MovieUiState> get() = _exploreData


    init {
        getTopRated()
    }


    private fun getTopRated() {
        viewModelScope.launch {
            repository.getTopRatedData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _exploreData.value = MovieUiState.Success(it.data.results)
                    }

                    is Resource.Error -> {
                        _exploreData.value = MovieUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _exploreData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            repository.getSearchData(query).collectLatest {
                when (it) {
                    is Resource.Success->{_searchData.value = MovieUiState.Success(it.data.results)}
                    is Resource.Error->{_searchData.value = MovieUiState.Error(it.exception.toString())}
                    is Resource.Loading->{_searchData.value = MovieUiState.Loading}
                }
            }
        }

    }
}