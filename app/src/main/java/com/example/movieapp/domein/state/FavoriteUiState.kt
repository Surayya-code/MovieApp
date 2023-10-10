package com.example.movieapp.domein.state

import com.example.movieapp.data.local.db.FavoriteDTO

sealed class FavoriteUiState {

    object Loading : FavoriteUiState()
    data class Success(val data: List<FavoriteDTO>) : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}