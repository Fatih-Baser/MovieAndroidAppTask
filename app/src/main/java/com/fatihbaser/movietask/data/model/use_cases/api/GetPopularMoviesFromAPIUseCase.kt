package com.fatihbaser.movietask.data.model.use_cases.api


import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.network.movies_responses.PopularMoviesInfoService
import timber.log.Timber

import javax.inject.Inject

class GetPopularMoviesFromAPIUseCase @Inject constructor(
    private val popularMoviesInfoService: PopularMoviesInfoService
) {
    suspend fun getData (
        languageCode: String = "en",
        countryCode: String = "US",
        resultsPage: Int = 1
    ): List<Movie> {
        try {
            val data = popularMoviesInfoService.searchMovies(languageCode, countryCode, resultsPage)
            return if (!data.isNullOrEmpty()) {
                data
            } else {
                Timber.e("GetPopularMoviesFromAPIUseCase couldn't found any value")
                //throw Exception("GetPopularMoviesFromAPIUseCase couldn't found any value")
                emptyList()
            }

        } catch (e: Exception) {
            Timber.e(e)
            throw Exception(e)
        }
    }
}