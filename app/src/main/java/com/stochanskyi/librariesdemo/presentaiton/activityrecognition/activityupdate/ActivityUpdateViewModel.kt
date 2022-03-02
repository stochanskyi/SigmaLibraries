package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActivityUpdateViewModel @Inject constructor(
    private val activityRecognitionDataSource: ActivityRecognitionDataSource
) : ViewModel() {

    private val _isServiceRunningLiveData = MutableLiveData<Boolean>()
    val isServiceRunningLiveData: LiveData<Boolean> = _isServiceRunningLiveData

    fun startOrStopUpdate() {
        val flow = activityRecognitionDataSource.getActivityUpdates()

        viewModelScope.launch {
            flow.collect {
                Log.d("ActivityRecRes", it.mostProbableActivity.toString())
            }
        }
    }
}