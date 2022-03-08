package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActivityUpdateViewModel @Inject constructor(
    private val observeActivityUpdateUseCase: ObserveActivityUpdateUseCase
) : ViewModel() {

    private val _isServiceRunningLiveData = MutableLiveData<Boolean>()
    val isServiceRunningLiveData: LiveData<Boolean> = _isServiceRunningLiveData

    fun startOrStopUpdate() {
        viewModelScope.launch {
            val result = observeActivityUpdateUseCase(3000)

            result.getOrNull()?.collect {
                Log.d("AC_REC_TAG", it.mostConfident.activityType.toString())
            }
        }

    }
}