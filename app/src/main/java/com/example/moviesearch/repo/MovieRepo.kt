package com.example.moviesearch.repo

interface MovieRepo {
    suspend fun searchMovie(movie: String, listener: MovieRepoImpl.MovieListener)
}