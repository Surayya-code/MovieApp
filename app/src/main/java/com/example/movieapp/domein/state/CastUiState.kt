package com.example.movieapp.domein.state

import com.example.movieapp.data.dto.CastingResponseModelItem

sealed class CastUiState{
    object Loading : CastUiState()
    data class Success(val data: CastingResponseModelItem) : CastUiState()
    data class Error(val message: String) : CastUiState()
}
