package com.fatihbaser.movietask.data.model.use_cases.room

import com.fatihbaser.movietask.data.local_database.MovieDao
import com.fatihbaser.movietask.data.model.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UpdateMovieInLocalDBUseCase @Inject constructor(
    private val dao: MovieDao
) {
    suspend fun updateData (
        movie: Movie
    ) {
        withContext(Dispatchers.IO) {
            try {
                dao.updateMovie(movie)
            } catch (e: Exception) {
                Timber.e("UpdateMovieInLocalDBUseCase couldn't update the Movie object")
                throw Exception(e)
            }
        }
    }
}