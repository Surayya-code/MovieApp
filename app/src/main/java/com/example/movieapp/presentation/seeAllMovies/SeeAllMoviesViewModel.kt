package com.example.movieapp.presentation.seeAllMovies

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
class SeeAllMoviesViewModel @Inject constructor(
    val repository: movieRepo
): ViewModel(){

    private val _movieData = MutableLiveData<MovieUiState>()
    val movieData: LiveData<MovieUiState> get() = _movieData

    fun getTop10() {
        viewModelScope.launch {
            repository.getTopRatedData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _movieData.value = MovieUiState.Success(it.data.results) }
                    is Resource.Error -> {
                        _movieData.value = MovieUiState.Error(it.exception.toString())
                    }
                    is Resource.Loading -> {
                        _movieData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }

    fun getNewRelease() {
        viewModelScope.launch {
            repository.getPopular().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _movieData.value = MovieUiState.Success(it.data.results)
                    }
                    is Resource.Error -> {
                        _movieData.value = MovieUiState.Error(it.exception.toString())
                    }
                    is Resource.Loading -> {
                        _movieData.value = MovieUiState.Loading
                    }
                }
            }
        }
    }
}