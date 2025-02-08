package com.androidlearning.sunnyweather.logic.dao

import com.androidlearning.sunnyweather.SunnyWeatherApplication
import com.androidlearning.sunnyweather.logic.model.DailyResponse
import com.androidlearning.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("/v2.6/${SunnyWeatherApplication.TOKEN}/{lngAndLat}/realtime")
    fun getRealtimeWeather(@Path("lngAndLat") lngAndLat: String): Call<RealtimeResponse>

    @GET("/v2.6/${SunnyWeatherApplication.TOKEN}/{lngAndLat}/daily?dailysteps=15")
    fun getDailyWeather(@Path("lngAndLat") lngAndLat: String): Call<DailyResponse>
}