package com.androidlearning.sunnyweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.androidlearning.sunnyweather.SunnyWeatherApplication.Companion.context
import com.androidlearning.sunnyweather.logic.dao.PlaceDao
import com.androidlearning.sunnyweather.logic.model.Place
import com.androidlearning.sunnyweather.logic.network.SunnyWeatherNetwork
import com.androidlearning.sunnyweather.logic.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(address: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlace(address)
        if (placeResponse.info == "OK") {
            val places = placeResponse.geocodes
            Result.success(places)
        } else {
            Result.failure(RuntimeException("查询位置状态：${placeResponse.status}"))
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()


    fun refreshWeather(lngAndLat: String) = fire(Dispatchers.IO) {

        coroutineScope {

            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lngAndLat)
            }

            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lngAndLat)
            }

            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()

            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(
                    realtimeResponse.result.realtime,
                    dailyResponse.result.daily
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }


    private fun <T> fire(
        context: CoroutineContext, block: suspend () -> Result<T>
    ): LiveData<Result<T>> {

        return liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
    }
}