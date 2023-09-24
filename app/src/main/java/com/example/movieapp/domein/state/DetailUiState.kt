package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.DetailsResponseModelItem

sealed class DetailUiState {

    object Loading : DetailUiState()
    data class Success(val data: DetailsResponseModelItem) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}
