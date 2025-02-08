package com.androidlearning.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val status: String,
        val temperature: Double,
        val humidity: Double,
        val cloudrate: Double,
        val skycon: String,
        val visibility: Double,
        val dswrf: Double,
        @SerializedName("wind") val wind: Wind,
        val pressure: Double,
        val apparent_temperature: Double,
        @SerializedName("precipitation") val precipitation: Precipitation,
        @SerializedName("air_quality") val airQuality: AirQuality,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Wind(
        val speed: Double,
        val direction: Double
    )

    data class Precipitation(
        val local: Local,
        val nearest: Nearest
    )

    data class Local(
        val status: String,
        val datasource: String,
        val intensity: Int
    )

    data class Nearest(
        val status: String,
        val distance: Double,
        val intensity: Double
    )

    data class AirQuality(
        val pm25: Int,
        val pm10: Int,
        val o3: Int,
        val so2: Int,
        val no2: Int,
        val co: Double,
        val aqi: Aqi,
        val description: Description
    )

    data class Aqi(val chn: Int, val usa: Int)

    data class Description(val chn: String, val usa: String)

    data class LifeIndex(val ultraviolet: Ultraviolet, val comfort: Comfort)

    data class Ultraviolet(val index: Int, val desc: String)

    data class Comfort(val index: Int, val desc: String)
}



