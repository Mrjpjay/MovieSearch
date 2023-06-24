package com.example.moviesearch.repo

import android.util.Log
import com.example.moviesearch.API_KEY
import com.example.moviesearch.api.MovieService
import com.example.moviesearch.api.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepoImpl : MovieRepo {

    private val TAG = MovieRepoImpl::class.simpleName
    override suspend fun searchMovie(movie: String, listener: MovieListener): Unit =
        withContext(Dispatchers.IO) {
            try {
                val call = MovieService().instance.searchMovie(API_KEY, movie).execute()
                if (call.isSuccessful) {
                    val list = mutableListOf<Movie>()
                    call.body()?.result?.forEach {
                        val title = it.title
                        val description = it.description
                        val posterPath = it.posterPath

                        val posterResponse =
                            MovieService().posterInstance.getPoster("$posterPath").execute()
                        val url = posterResponse.raw().request.url
                        list.add(Movie(title, description,"$url"))
                    }
                    listener.onSuccess(list)
                } else {
                    Log.i(TAG,"Error ${call.body()}")
                    listener.onError("not successful ${call.body()}")
                }
            } catch (e: Exception) {
                Log.i(TAG,"Exception $e")
                listener.onError("exception $e")
            }
        }

    interface MovieListener {
        fun onSuccess(list: List<Movie>)
        fun onError(e: String)
    }
}