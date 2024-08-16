package com.fatihbaser.movietask.data.local_database.converters

import androidx.room.TypeConverter
import com.fatihbaser.movietask.data.model.entity.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LanguageConverter {
    @TypeConverter
    fun stringToLanguageList(value: String?): List<Language?>? {
        val listType: Type = object : TypeToken<ArrayList<Language?>?>() {}.type
        return Gson().fromJson<List<Language?>>(value, listType)
    }
    @TypeConverter
    fun languageListToString(list: List<Language?>?): String? {
        return Gson().toJson(list)
    }
}