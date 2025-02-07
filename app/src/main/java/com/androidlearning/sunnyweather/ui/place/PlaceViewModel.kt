package com.androidlearning.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.androidlearning.sunnyweather.logic.Repository
import com.androidlearning.sunnyweather.logic.model.Place

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = searchLiveData.switchMap { address ->
        Repository.searchPlaces(address)
    }

    fun searchPlaces(address: String) {
        searchLiveData.value = address
    }

}