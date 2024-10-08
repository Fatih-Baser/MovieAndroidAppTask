package com.fatihbaser.movietask.data.model.repository


import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.use_cases.general.SearchMovieFromRepositoryUseCase
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    private val searchMovieFromRepositoryUseCase: SearchMovieFromRepositoryUseCase
) {

    suspend fun getMoviesFromSearch (
        movieName: String,
        internetConnection: Boolean,
        refresh: Boolean = false
    ) : List<Movie> {
        try {
            return searchMovieFromRepositoryUseCase.getData(movieName, internetConnection, refresh)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}