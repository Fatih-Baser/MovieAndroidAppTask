package com.fatihbaser.movietask.data.model.entity

import com.google.gson.annotations.SerializedName

data class Videos (
    @SerializedName("results") var videosFound: List<Video>? = emptyList(), // List of Videos found
)