package com.androidlearning.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.Date


data class DailyResponse(val status: String, val result: Result) {

    data class Result(val daily: Daily)

    data class Daily(
        val status: String,
        val astro: List<Astro>,
        val precipitation08h20h: List<Precipitation>,
        val precipitation20h32h: List<Precipitation>,
        val precipitation: List<Precipitation>,
        val temperature: List<Temperature>,
        val temperature08h20h: List<Temperature>,
        val temperature20h32h: List<Temperature>,
        val wind: List<Wind>,
        val wind08h20h: List<Wind>,
        val wind20h32h: List<Wind>,
        val humidity: List<Humidity>,
        val cloudrate: List<Cloudrate>,
        val pressure: List<Pressure>,
        val visibility: List<Visibility>,
        val dswrf: List<Dswrf>,
        @SerializedName("air_quality") val airQuality: AirQuality,
        val skycon: List<Skycon>,
        val skycon08h20h: List<Skycon>,
        val skycon20h32h: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Astro(
        val date: Date,
        val sunrise: SunriseSunset,
        val sunset: SunriseSunset
    )

    data class SunriseSunset(
        val time: String
    )

    data class Precipitation(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double,
        val probability: Int
    )

    data class Temperature(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class Wind(
        val date: Date,
        val max: WindDirectionSpeed,
        val min: WindDirectionSpeed,
        val avg: WindDirectionSpeed
    )

    data class WindDirectionSpeed(
        val speed: Double,
        val direction: Double
    )

    data class Humidity(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class Cloudrate(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class Pressure(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class Visibility(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class Dswrf(
        val date: Date,
        val max: Double,
        val min: Double,
        val avg: Double
    )

    data class AirQuality(
        val aqi: List<Aqi>,
        val pm25: List<Pm25>
    )

    data class Aqi(
        val date: Date,
        val max: AqiValue,
        val avg: AqiValue,
        val min: AqiValue
    )

    data class AqiValue(
        val chn: Int,
        val usa: Int
    )

    data class Pm25(
        val date: Date,
        val max: Int,
        val avg: Int,
        val min: Int
    )

    data class Skycon(
        val date: Date,
        val value: String
    )

    data class LifeIndex(
        val ultraviolet: List<LifeIndexItem>,
        val carWashing: List<LifeIndexItem>,
        val dressing: List<LifeIndexItem>,
        val comfort: List<LifeIndexItem>,
        val coldRisk: List<LifeIndexItem>
    )

    data class LifeIndexItem(
        val date: Date,
        val index: String,
        val desc: String
    )
} 

