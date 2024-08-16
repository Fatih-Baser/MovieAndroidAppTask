package com.fatihbaser.movietask.data.local_database.converters

import androidx.room.TypeConverter
import com.fatihbaser.movietask.data.model.entity.ProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ProductionCompanyConverter {
    @TypeConverter
    fun stringToProductionCompanyList(value: String?): List<ProductionCompany?>? {
        val listType: Type = object : TypeToken<ArrayList<ProductionCompany?>?>() {}.type
        return Gson().fromJson<List<ProductionCompany?>>(value, listType)
    }
    @TypeConverter
    fun productionCompanyListToString(list: List<ProductionCompany?>?): String? {
        return Gson().toJson(list)
    }
}