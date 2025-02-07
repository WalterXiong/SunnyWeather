package com.androidlearning.sunnyweather.logic.dao

import com.androidlearning.sunnyweather.SunnyWeatherApplication
import com.androidlearning.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v3/geocode/geo?key=${SunnyWeatherApplication.GEO_KEY}")
    fun searchPlaces(@Query("address") address: String): Call<PlaceResponse>
}