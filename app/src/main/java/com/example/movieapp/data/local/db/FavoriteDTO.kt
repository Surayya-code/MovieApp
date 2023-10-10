package com.example.movieapp.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("movie_list")
data class FavoriteDTO(
    @PrimaryKey(false)
    val id: Int,
    val title: String,
    val image: String,
    val vote: Double
)
