package com.fatihbaser.movietask.data.model.repository

import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.use_cases.general.GetNowPlayingMoviesFromRepositoryUseCase
import com.fatihbaser.movietask.data.model.use_cases.room.GetUserFavoriteMoviesFromLocalDBUseCase
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val getNowPlayingMoviesFromRepositoryUseCase: GetNowPlayingMoviesFromRepositoryUseCase,
    private val getUserFavoriteMoviesFromLocalDBUseCase: GetUserFavoriteMoviesFromLocalDBUseCase
) {

    suspend fun getUserFavoriteMovies (

    ): List<Movie> {
        try {
            return getUserFavoriteMoviesFromLocalDBUseCase.getData()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getNowPlayingMovies (
        internetConnection: Boolean,
        refresh: Boolean = false
    ) : List<Movie> {
        try {
            return getNowPlayingMoviesFromRepositoryUseCase.getData(internetConnection, refresh)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}