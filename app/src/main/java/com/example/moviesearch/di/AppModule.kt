package com.example.moviesearch.di

import com.example.moviesearch.repo.MovieRepo
import com.example.moviesearch.repo.MovieRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesMovieRepository(): MovieRepo{
        return MovieRepoImpl()
    }
}