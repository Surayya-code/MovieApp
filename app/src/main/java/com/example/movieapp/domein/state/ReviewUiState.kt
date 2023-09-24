package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.ReviewsResponseModelItem

sealed class ReviewUiState{
    object Loading : ReviewUiState()
    data class Success(val data: ReviewsResponseModelItem) : ReviewUiState()
    data class Error(val message: String) : ReviewUiState()
}

