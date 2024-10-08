package com.fatihbaser.movietask.domain

import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.repository.MoviesRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getData (
        internetConnection: Boolean,
        refresh: Boolean = false
    ) : List<Movie> {
        try {
            return moviesRepository.getNowPlayingMovies(internetConnection, refresh)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}