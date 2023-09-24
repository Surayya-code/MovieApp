package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.RecommendationResponseModelItem

sealed class RecommendationUiState{
    object Loading : RecommendationUiState()
    data class Success(val data: RecommendationResponseModelItem) : RecommendationUiState()
    data class Error(val message: String) : RecommendationUiState()
}