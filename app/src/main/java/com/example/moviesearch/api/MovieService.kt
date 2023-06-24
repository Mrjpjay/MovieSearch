package com.example.moviesearch.api

import com.example.moviesearch.BASE_URL
import com.example.moviesearch.POSTER_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {

    private lateinit var retrofit: Retrofit
    private lateinit var retrofit2: Retrofit

    val instance: MovieEndPoints by lazy {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieEndPoints::class.java)
    }

    val posterInstance : MovieEndPoints by lazy {
        retrofit2 = Retrofit.Builder()
            .baseUrl(POSTER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit2.create(MovieEndPoints::class.java)
    }
}