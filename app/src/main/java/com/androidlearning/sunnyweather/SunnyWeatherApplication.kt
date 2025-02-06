package com.androidlearning.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN: String = "tL1q0fnbSs3NVbbD"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}