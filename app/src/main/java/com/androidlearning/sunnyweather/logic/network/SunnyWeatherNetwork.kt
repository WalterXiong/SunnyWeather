package com.androidlearning.sunnyweather.logic.network

import com.androidlearning.sunnyweather.logic.dao.PlaceService
import com.androidlearning.sunnyweather.logic.dao.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    // ====================================================================================
    // 请求地理位置数据
    // ====================================================================================
    /**
     * 泛型实化的应用
     */
    private val placeService = PlaceServiceCreator.create<PlaceService>()

    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

    // ====================================================================================
    // 请求天气数据
    // ====================================================================================

    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getRealtimeWeather(lngAndLat: String) =
        weatherService.getRealtimeWeather(lngAndLat).await()

    suspend fun getDailyWeather(lngAndLat: String) =
        weatherService.getDailyWeather(lngAndLat).await()


    /**
     * 使用 suspendCoroutine 简化回调写法
     */
    private suspend fun <T> Call<T>.await(): T {

        return suspendCoroutine { continuation ->

            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T?>, response: Response<T?>) {

                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("返回值为空"))
                    }
                }

                override fun onFailure(call: Call<T?>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}