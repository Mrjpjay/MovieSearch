package com.example.moviesearch.repo

import android.util.Log
import com.example.moviesearch.API_KEY
import com.example.moviesearch.api.MovieService
import com.example.moviesearch.api.data.Movie
import com.example.moviesearch.api.data.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepoImpl : MovieRepo {
    override fun searchMovie(movie: String, listener: MovieListener) {
        try {
            MovieService().instance.searchMovie(API_KEY, movie)
                .enqueue(object : Callback<MovieResult> {
                    override fun onResponse(
                        call: Call<MovieResult>,
                        response: Response<MovieResult>
                    ) {
                        if(response.isSuccessful){
                            val list = mutableListOf<Movie>()
                            response.body()?.result?.forEach {
                                val title = it.title
                                val description = it.description
                                list.add(Movie(title,description))
                                listener.onSuccess(list)
                            }
                        } else {
                            listener.onError("not successful $response")
                        }
                    }

                    override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                        listener.onError("onFailure ${t.message}")
                    }
                })
        } catch (e: Exception) {
            listener.onError("exception $e")
        }
    }

    interface MovieListener {
        fun onSuccess(list: List<Movie>)
        fun onError(e: String)
    }
}