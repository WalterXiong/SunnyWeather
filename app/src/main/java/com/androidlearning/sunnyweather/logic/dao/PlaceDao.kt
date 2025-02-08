package com.androidlearning.sunnyweather.logic.dao

import android.content.Context
import android.content.SharedPreferences
import com.androidlearning.sunnyweather.SunnyWeatherApplication
import com.androidlearning.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {

    private fun sharedPreferences() = SunnyWeatherApplication.context
        .getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }


    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
}

fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
    val edit = edit()
    edit.block()
    edit.apply()
}