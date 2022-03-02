package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ActivityUpdateViewModel @Inject constructor() : ViewModel() {

    private val _isServiceRunningLiveData = MutableLiveData<Boolean>()
    val isServiceRunningLiveData: LiveData<Boolean> = _isServiceRunningLiveData

    fun startOrStopUpdate() {

    }
}