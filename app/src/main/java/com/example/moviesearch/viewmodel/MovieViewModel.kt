package com.example.moviesearch.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearch.api.data.Movie
import com.example.moviesearch.repo.MovieRepo
import com.example.moviesearch.repo.MovieRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieRepo
) : ViewModel(){

    private val _list = MutableStateFlow(listOf<Movie>())
    val list = _list.asStateFlow()

    init {
        searchMovie("Harry Potter")
    }
    fun searchMovie(movie: String){
        viewModelScope.launch {
            repo.searchMovie(movie,object : MovieRepoImpl.MovieListener{
                override fun onSuccess(list: List<Movie>) {
                    _list.value = list
                }

                override fun onError(e: String) {
                }
            })
        }
    }
}