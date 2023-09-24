package com.example.movieapp.data.dto

import com.google.gson.annotations.SerializedName

data class PopularResponseModelItem(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<DetailsResponseModelItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)