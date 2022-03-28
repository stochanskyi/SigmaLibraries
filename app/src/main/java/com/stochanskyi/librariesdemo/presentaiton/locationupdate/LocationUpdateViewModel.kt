package com.stochanskyi.librariesdemo.presentaiton.locationupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import javax.inject.Inject

class LocationUpdateViewModel @Inject constructor() : ViewModel() {

    private val _locationLiveData = MutableLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = _locationLiveData

    fun startOrStop() {

    }
}