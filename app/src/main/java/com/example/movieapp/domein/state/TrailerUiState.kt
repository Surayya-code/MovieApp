package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.TrailerResponseModelItem

sealed class TrailerUiState{
    object Loading : TrailerUiState()
    data class Success(val data: TrailerResponseModelItem) : TrailerUiState()
    data class Error(val message: String) : TrailerUiState()
}
