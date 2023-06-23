package com.example.moviesearch.repo

interface MovieRepo {
    fun searchMovie(movie: String, listener: MovieRepoImpl.MovieListener)
}