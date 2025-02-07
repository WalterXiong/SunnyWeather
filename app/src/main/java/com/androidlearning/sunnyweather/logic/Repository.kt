package com.androidlearning.sunnyweather.logic

import androidx.lifecycle.liveData
import com.androidlearning.sunnyweather.logic.network.SunnyWeatherNetwork
import com.androidlearning.sunnyweather.logic.model.Place
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(address: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlace(address)
            if (placeResponse.info == "OK") {
                val places = placeResponse.geocodes
                Result.success(places)
            } else {
                Result.failure(RuntimeException("查询位置状态：${placeResponse.status}"))
            }

        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}