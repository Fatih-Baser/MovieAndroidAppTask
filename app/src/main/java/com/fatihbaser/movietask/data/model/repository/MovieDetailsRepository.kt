package com.fatihbaser.movietask.data.model.repository

import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.use_cases.general.AddOrRemoveUserFavoriteMoviesInLocalDBUseCase
import com.fatihbaser.movietask.data.model.use_cases.general.GetAllDetailsOfAMovieFromRepositoryUseCase
import com.fatihbaser.movietask.data.model.use_cases.general.GetSimilarMoviesFromRepositoryUseCase
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val getAllDetailsOfAMovieFromRepositoryUseCase: GetAllDetailsOfAMovieFromRepositoryUseCase,
    private val addOrRemoveUserFavoriteMoviesInLocalDBUseCase: AddOrRemoveUserFavoriteMoviesInLocalDBUseCase,
    private val getSimilarMoviesFromRepositoryUseCase: GetSimilarMoviesFromRepositoryUseCase,
) {

    suspend fun getMovieDetails (
        movieId: Int,
        internetConnection: Boolean,
        refresh: Boolean = false,
        languageCode: String = "en",
        countryCode: String = "US"
    ) : Movie {
        try {
            return getAllDetailsOfAMovieFromRepositoryUseCase.getData(movieId, internetConnection, refresh, languageCode, countryCode)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun addOrRemoveMovieOnUserFavoriteMovies (
        movieId: Int
    ): Movie {
        try {
            return addOrRemoveUserFavoriteMoviesInLocalDBUseCase.addorRemoveData(movieId)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getListOfMoviesFromMovieIds (
        movieId: Int,
        similarMoviesIds: List<Int>,
        internetConnection: Boolean,
        refresh: Boolean = false,
        languageCode: String = "en",
        countryCode: String = "US"
    ): List<Movie> {
        try {
            return getSimilarMoviesFromRepositoryUseCase.getData(movieId, similarMoviesIds, internetConnection, refresh, languageCode, countryCode)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}