package com.fatihbaser.movietask.ui.viewmodel.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatihbaser.movietask.auxiliar.internet_connection.InternetConnectionHelper
import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.domain.GetNowPlayingMoviesUseCase
import com.fatihbaser.movietask.domain.GetUserFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val internetConnectionHelper: InternetConnectionHelper,
    private val getUserFavoriteMoviesUseCase: GetUserFavoriteMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
): ViewModel() {
    // LIVE DATA VARS
    // Progress bar
    private val _setProgressVisibility = MutableLiveData<Boolean>()
    val setProgressVisibility : LiveData<Boolean> get() = _setProgressVisibility
    // Show info msg

    // Navigation
    private val _goToMovieInfoFragment = MutableLiveData<Int?>()
    val goToMovieInfoFragment : LiveData<Int?> get() = _goToMovieInfoFragment
    // Refreshing
    private val _refreshVisibility = MutableLiveData<Boolean>()
    val refreshVisibility : LiveData<Boolean> get() = _refreshVisibility
    // Recycler View DATA

    // User Favorite Movies
    private val _userFavoriteMoviesData = MutableLiveData<List<Movie>>()
    val userFavoriteMoviesData : LiveData<List<Movie>> get() = _userFavoriteMoviesData
    // Now Playing Movies
    private val _nowPlayingMoviesData = MutableLiveData<List<Movie>>()
    val nowPlayingMoviesData : LiveData<List<Movie>> get() = _nowPlayingMoviesData

    // No favorite movies msg
    private val _setNoFavoriteMoviesVisibility = MutableLiveData<Boolean>()
    val setNoFavoriteMoviesVisibility : LiveData<Boolean> get() = _setNoFavoriteMoviesVisibility
    // No now playing movies
    private val _setNoNowPlayingMoviesVisibility = MutableLiveData<Boolean>()
    val setNoNowPlayingMoviesVisibility : LiveData<Boolean> get() = _setNoNowPlayingMoviesVisibility
    // No upcoming movies


    fun onCreate() {
        _refreshVisibility.value = false
        // We make sure it doesn't just go to the other Fragment
        _goToMovieInfoFragment.value = null
        viewModelScope.launch {
            getDataToFillUserFavoriteMoviesRecyclerView()
            getDataToFillNowPlayingMoviesRecyclerView()
        }
    }

    // User Favorite Movies
    private suspend fun getDataToFillUserFavoriteMoviesRecyclerView() {
        try {
            // Show the progress bar
            _setProgressVisibility.postValue(true)
            // Get a list of User Favorite Movies from the model via Use Case
            val userFavoriteMovies: List<Movie> = getUserFavoriteMoviesUseCase.getData()
            // Verify if the list of movies is null or empty
            if (!userFavoriteMovies.isNullOrEmpty()) {
                // Send the info to fill the recyclerView
                _userFavoriteMoviesData.postValue(userFavoriteMovies)
                _setNoFavoriteMoviesVisibility.postValue(false)
            } else {
                // Send a empty List to the recyclerView
                _userFavoriteMoviesData.postValue(emptyList())
                // Msg to the user
                _setNoFavoriteMoviesVisibility.postValue(true)
            }

        } catch (e: Exception) {
            // Send a empty List to the recyclerView
            _userFavoriteMoviesData.postValue(emptyList())
            // Msg to the user
            _setNoFavoriteMoviesVisibility.postValue(true)
        } finally {
            _setProgressVisibility.postValue(false)
        }
    }

    // Now Playing Movies
    private suspend fun getDataToFillNowPlayingMoviesRecyclerView (
        refresh: Boolean = false
    ) {
        try {
            // Show the progress bar
            _setProgressVisibility.postValue(true)
            // Get the internet state of the device
            val internetConnectionState = internetConnectionHelper.internetIsConnected()
            // Get a list of Popular Movies from the model via Use Case
            val nowPlayingMovies: List<Movie> = getNowPlayingMoviesUseCase.getData(internetConnectionState, refresh)
            // Verify if the list of movies is null or empty
            if (!nowPlayingMovies.isNullOrEmpty()) {
                // Send the info to fill the recyclerView
                _nowPlayingMoviesData.postValue(nowPlayingMovies)
                // msg to the user
                _setNoNowPlayingMoviesVisibility.postValue(false)
            } else {
                // Send a empty List to the recyclerView
                _nowPlayingMoviesData.postValue(emptyList())
                // msg to the user
                _setNoNowPlayingMoviesVisibility.postValue(true)
            }
        } catch (e: Exception) {
            // Send a empty List to the recyclerView
            _nowPlayingMoviesData.postValue(emptyList())
            // msg to the user
            _setNoNowPlayingMoviesVisibility.postValue(true)
        } finally {
            // Hide the progress bar
            _setProgressVisibility.postValue(false)
        }
    }



    fun onMovieClicked(movieIDSelected: String) {
        _goToMovieInfoFragment.value = movieIDSelected.toInt()
    }

    fun onRefresh() {
        viewModelScope.launch {
            getDataToFillUserFavoriteMoviesRecyclerView()
            getDataToFillNowPlayingMoviesRecyclerView(true)
            _refreshVisibility.value = false
        }
    }

    fun navigationCompleted() {
        _goToMovieInfoFragment.value = null
    }
}