package com.fatihbaser.movietask.data.model.use_cases.room


import com.fatihbaser.movietask.data.local_database.MovieDao
import com.fatihbaser.movietask.data.model.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetUserFavoriteMoviesFromLocalDBUseCase @Inject constructor(
    private val dao: MovieDao
) {
    suspend fun getData (

    ): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val data = dao.getUserFavoriteMovies()
                if (!data.isNullOrEmpty()){
                    data
                } else {
                    Timber.e("GetUserFavoriteMoviesFromLocalDBUseCase couldn't found any value")
                    emptyList()
                }

            } catch (e: Exception) {
                throw Exception("GetUserFavoriteMoviesFromLocalDBUseCase couldn't found any value")
            }
        }
    }
}