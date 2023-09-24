package com.example.movieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GiveRatingViewModel  @Inject constructor(
    private val movieRepository: movieRepo,
): ViewModel() {

    private val _detailMovies= MutableLiveData<DetailUiState>()
    val detailMovies: LiveData<DetailUiState> = _detailMovies

    fun getDetailMovie(id : Int) {
        viewModelScope.launch {
            movieRepository.getDetails(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _detailMovies.value = DetailUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _detailMovies.value = DetailUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _detailMovies.value = DetailUiState.Loading
                    }


                }
            }
        }
    }
}