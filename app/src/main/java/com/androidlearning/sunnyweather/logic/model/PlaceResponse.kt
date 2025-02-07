package com.androidlearning.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    val status: String,
    val info: String,
    val infocode: String,
    val count: Int,
    val geocodes: List<Place>
)

data class Place(
    val name: String,
    val location: String,
    @SerializedName("formatted_address") val address: String
)
