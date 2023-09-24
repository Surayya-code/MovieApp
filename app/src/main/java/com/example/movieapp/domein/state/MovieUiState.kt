package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.DetailsResponseModelItem

sealed class MovieUiState {

    object Loading : MovieUiState()
    data class Success(val data: List<DetailsResponseModelItem>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}