package com.androidlearning.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlaceServiceCreator {

    private const val BASE_URL: String = "https://restapi.amap.com/"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 泛型实化，优化代码
     */
    inline fun <reified T> create(): T = create(T::class.java)
}