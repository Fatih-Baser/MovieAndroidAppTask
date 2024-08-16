package com.fatihbaser.movietask.data.local_database.converters

import androidx.room.TypeConverter
import com.fatihbaser.movietask.data.model.entity.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ReviewConverter {
    @TypeConverter
    fun stringToReviewList(value: String?): List<Review?>? {
        val listType: Type = object : TypeToken<ArrayList<Review?>?>() {}.type
        return Gson().fromJson<List<Review?>>(value, listType)
    }
    @TypeConverter
    fun reviewListToString(list: List<Review?>?): String? {
        return Gson().toJson(list)
    }
}