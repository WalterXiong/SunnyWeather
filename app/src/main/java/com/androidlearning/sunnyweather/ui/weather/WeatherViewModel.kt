package com.androidlearning.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.androidlearning.sunnyweather.logic.Repository

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<String>()

    var locationLngAndLat = ""

    var placeName = ""

    val weatherLiveData = locationLiveData.switchMap { lngAndLat ->
        Repository.refreshWeather(lngAndLat)
    }

    fun refreshWeather(lngAndLat: String) {
        locationLiveData.value = lngAndLat
    }
}