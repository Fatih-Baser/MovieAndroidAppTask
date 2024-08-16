package com.fatihbaser.movietask.data.local_database.converters

import androidx.room.TypeConverter
import com.fatihbaser.movietask.data.model.entity.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GenreConverter {
    @TypeConverter
    fun stringToGenreList(value: String?): List<Genre?>? {
        val listType: Type = object : TypeToken<ArrayList<Genre?>?>() {}.type
        return Gson().fromJson<List<Genre?>>(value, listType)
    }
    @TypeConverter
    fun genreListToString(list: List<Genre?>?): String? {
        return Gson().toJson(list)
    }
}