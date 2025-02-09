package com.androidlearning.sunnyweather.utils

import android.widget.Toast
import com.androidlearning.sunnyweather.SunnyWeatherApplication


fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(SunnyWeatherApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(SunnyWeatherApplication.context, this, duration).show()
}
