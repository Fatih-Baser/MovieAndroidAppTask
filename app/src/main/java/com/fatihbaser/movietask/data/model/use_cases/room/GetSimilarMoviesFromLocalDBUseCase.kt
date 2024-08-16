package com.fatihbaser.movietask.data.model.use_cases.room


import com.fatihbaser.movietask.data.local_database.MovieDao
import com.fatihbaser.movietask.data.model.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetSimilarMoviesFromLocalDBUseCase @Inject constructor(
    private val dao: MovieDao
) {
    suspend fun getData (
        similarMoviesIds: List<Int>
    ): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val movies: MutableList<Movie> = ArrayList<Movie>()
                for (movieID in similarMoviesIds) {
                    val movie = dao.getMovie(movieID)
                    if (movie != null) {
                        movies.add(movie)
                    }
                }
                if (!movies.isNullOrEmpty()){
                    movies
                } else {
                    Timber.e("GetSimilarMoviesFromLocalDBUseCase couldn't found any value")
                    emptyList<Movie>()
                }

            } catch (e: Exception) {
                Timber.e(e)
                throw Exception("GetSimilarMoviesFromLocalDBUseCase couldn't found any value")
            }
        }
    }
}