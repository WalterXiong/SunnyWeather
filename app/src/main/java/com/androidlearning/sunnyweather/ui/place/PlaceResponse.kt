package com.androidlearning.sunnyweather.ui.place

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val place: List<String>)

data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)