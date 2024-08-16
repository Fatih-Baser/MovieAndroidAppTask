package com.fatihbaser.movietask.domain


import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.repository.MoviesRepository
import javax.inject.Inject

class GetUserFavoriteMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getData(

    ): List<Movie> {
        try {
            return moviesRepository.getUserFavoriteMovies()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}