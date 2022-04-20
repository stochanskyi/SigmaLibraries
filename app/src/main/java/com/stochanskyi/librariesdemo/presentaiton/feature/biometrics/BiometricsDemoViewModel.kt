package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class BiometricsDemoViewModel @Inject constructor() : ViewModel() {

    private val _restoredLiveData = MutableLiveData<String>()
    val restoredLiveData: LiveData<String> = _restoredLiveData

    fun saveData(data: String) {

    }

    fun restoreData() {

    }
}