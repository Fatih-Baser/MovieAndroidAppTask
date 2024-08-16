package com.fatihbaser.movietask.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fatihbaser.movietask.data.local_database.converters.ActorConverter
import com.fatihbaser.movietask.data.local_database.converters.GenreConverter
import com.fatihbaser.movietask.data.local_database.converters.LanguageConverter
import com.fatihbaser.movietask.data.local_database.converters.IntConverter
import com.fatihbaser.movietask.data.local_database.converters.ProductionCompanyConverter
import com.fatihbaser.movietask.data.local_database.converters.ReviewConverter
import com.fatihbaser.movietask.data.local_database.converters.VideoConverter

import com.fatihbaser.movietask.data.model.entity.Movie


// Creating the Database with room
// 1 Table (Entity) -> Movie (movies)
@Database (
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
// Adding converters to use lists of custom objects
@TypeConverters(
    value = [
        ActorConverter::class,
        GenreConverter::class,
        LanguageConverter::class,
        IntConverter::class,
        ProductionCompanyConverter::class,
        ReviewConverter::class,
        VideoConverter::class
    ]
)
abstract class RoomMovieDatabase
    : RoomDatabase() {
        abstract fun movieDao(): MovieDao
}