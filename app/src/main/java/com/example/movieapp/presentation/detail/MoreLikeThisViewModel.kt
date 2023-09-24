package com.example.movieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.RecommendationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreLikeThisViewModel @Inject constructor(
    private val movieRepository: movieRepo
) : ViewModel(){
    private val _recommendation= MutableLiveData<RecommendationUiState>()
    val recommendation: LiveData<RecommendationUiState> = _recommendation


    fun getMoreLikeThis(id: Int) {
        viewModelScope.launch {
            movieRepository.getRecommendations(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _recommendation.value = RecommendationUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _recommendation.value = RecommendationUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _recommendation.value = RecommendationUiState.Loading
                    }
                }
            }
        }
    }

}