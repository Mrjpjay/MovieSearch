package com.example.moviesearch.api

import com.example.moviesearch.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {

    private lateinit var retrofit: Retrofit

    val instance: MovieEndPoints by lazy {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieEndPoints::class.java)
    }
}