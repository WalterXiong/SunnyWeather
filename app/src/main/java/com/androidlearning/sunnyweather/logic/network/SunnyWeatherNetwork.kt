package com.androidlearning.sunnyweather.logic.network

import com.androidlearning.sunnyweather.logic.dao.PlaceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    /**
     * 泛型实化的应用
     */
    private val placeService = PlaceServiceCreator.create<PlaceService>()

    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

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