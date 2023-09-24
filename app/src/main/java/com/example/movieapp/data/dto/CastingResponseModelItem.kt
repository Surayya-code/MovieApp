package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName

data class CastingResponseModelItem(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)