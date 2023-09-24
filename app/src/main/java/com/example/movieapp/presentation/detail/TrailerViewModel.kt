package com.example.movieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.TrailerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel  @Inject constructor(
    private val movieRepository: movieRepo
): ViewModel(){

    private val _trailer= MutableLiveData<TrailerUiState>()
    val trailer: LiveData<TrailerUiState> = _trailer


    fun getTrailer(id: Int) {
        viewModelScope.launch {
            movieRepository.getTrailers(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _trailer.value = TrailerUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _trailer.value = TrailerUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _trailer.value = TrailerUiState.Loading
                    }
                }
           }
        }
    }

}