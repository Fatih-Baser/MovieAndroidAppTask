package com.fatihbaser.movietask.data.local_database.converters

import androidx.room.TypeConverter
import com.fatihbaser.movietask.data.model.entity.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class VideoConverter {
    @TypeConverter
    fun stringToVideoList(value: String?): List<Video?>? {
        val listType: Type = object : TypeToken<ArrayList<Video?>?>() {}.type
        return Gson().fromJson<List<Video?>>(value, listType)
    }
    @TypeConverter
    fun videoListToString(list: List<Video?>?): String? {
        return Gson().toJson(list)
    }
}