package com.fatihbaser.movietask.data.model.entity

import com.google.gson.annotations.SerializedName

data class ProductionCompany (
    @SerializedName("name") var name: String? = "",
    @SerializedName("logo_path") var logoImg: String? = "",
    @SerializedName("origin_country") var isoCountryCode: String? = "",
)