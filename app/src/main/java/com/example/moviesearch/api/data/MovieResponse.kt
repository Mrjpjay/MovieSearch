package com.example.moviesearch.api.data

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("results") val result: List<MovieResponse>
)
data class MovieResponse(
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val description: String
)
