package com.example.moviesearch.api

import com.example.moviesearch.api.data.MovieResponse
import com.example.moviesearch.api.data.MovieResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndPoints {

    @GET("3/search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") movie: String): Call<MovieResult>

    @GET("t/p/w600_and_h900_bestv2/")
    fun getPoster(@Path("poster") poster: String): Call<ResponseBody>
}