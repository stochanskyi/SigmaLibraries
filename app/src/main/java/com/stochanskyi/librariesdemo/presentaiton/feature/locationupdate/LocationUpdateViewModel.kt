package com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobDefinition
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobExecutor
import com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs.UpdateLocationJobDefinition
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationUpdateViewModel @Inject constructor(
    private val jobExecutor: JobExecutor
) : ViewModel() {

    private val jobDefinition: JobDefinition = UpdateLocationJobDefinition()

    private val _locationLiveData = MutableLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = _locationLiveData

    fun startOrStopUpdates() {

        viewModelScope.launch {
            if (isJobRunning()) {
                jobExecutor.stopJob(jobDefinition.tag)
            } else {
                jobExecutor.execute(jobDefinition)
            }
        }
    }

    private suspend fun isJobRunning(): Boolean {
        return jobExecutor.isJobRunning(jobDefinition.tag)
    }
}