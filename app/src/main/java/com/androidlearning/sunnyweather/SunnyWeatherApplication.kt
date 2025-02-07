package com.androidlearning.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN: String = "tL1q0fnbSs3NVbbD"

        const val GEO_KEY: String = "fc29bda87a4e11fc8025c44be02df786"

        const val AK: String = "rWvVz740Gt83bG642UeVFXwArjnkCinO"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}