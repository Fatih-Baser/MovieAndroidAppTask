package com.fatihbaser.movietask.data.local_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fatihbaser.movietask.data.model.entity.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :idMovie LIMIT 1")
    fun getMovie(idMovie: Int): Movie?

    @Query("SELECT * FROM movies WHERE isNowPlayingMovie = 1 ORDER BY movie_popularity DESC")
    fun getNowPlayingMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isUserFavoriteMovie = 1")
    fun getUserFavoriteMovies(): List<Movie>

    @Update
    fun updateMovie(movie: Movie)
}