package com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate

import androidx.lifecycle.*
import androidx.work.Data
import androidx.work.WorkInfo
import androidx.work.hasKeyWithValueOfType
import com.stochanskyi.librariesdemo.domain.features.location.models.LocationData
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobDefinition
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobExecutor
import com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs.UpdateLocationJobDefinition
import com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs.UpdateLocationWorker
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class LocationUpdateViewModel @Inject constructor(
    private val jobExecutor: JobExecutor
) : ViewModel() {

    private val jobDefinition: JobDefinition = UpdateLocationJobDefinition()

    private val _locationLiveData = MediatorLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = _locationLiveData

    private val _isUpdatingRunningLiveData = MutableLiveData<Boolean>()
    val isUpdatingRunningLiveData: LiveData<Boolean> = _isUpdatingRunningLiveData

    private var locationWorkerProgressLiveData: LiveData<WorkInfo>? = null

    init {
        viewModelScope.launch {
            _isUpdatingRunningLiveData.value = isJobRunning()
        }
    }

    fun startOrStopUpdates() {

        viewModelScope.launch {
            val isUpdatingRunning = isJobRunning()
            if (isUpdatingRunning) {
                jobExecutor.stopJob(jobDefinition.tag)
                clearLocationObserver()
            } else {
                val id = jobExecutor.execute(jobDefinition)
                observeLocationChanges(id)
            }
            _isUpdatingRunningLiveData.value = !isUpdatingRunning
        }
    }

    private fun observeLocationChanges(id: UUID) {
        locationWorkerProgressLiveData = jobExecutor.observeJobState(id)
            .also { addLocationSource(it) }
    }

    private fun addLocationSource(liveData: LiveData<WorkInfo>) {
        _locationLiveData.addSource(liveData) {
            val location = it.progress.toLocationData() ?: return@addSource
            _locationLiveData.value = location
        }
    }

    private fun clearLocationObserver() {
        _locationLiveData.removeSource(locationWorkerProgressLiveData ?: return)
        locationWorkerProgressLiveData = null
    }

    private suspend fun isJobRunning(): Boolean {
        return jobExecutor.isJobRunning(jobDefinition.tag)
    }

    private fun Data.toLocationData(): LocationData? {
        if (!this.hasKeyWithValueOfType<Float>(UpdateLocationWorker.LAT_PROGRESS_KEY) ||
            !this.hasKeyWithValueOfType<Float>(UpdateLocationWorker.LONG_PROGRESS_KEY)
        ) {
            return null
        }
        return LocationData(
            lat = getFloat(UpdateLocationWorker.LAT_PROGRESS_KEY, 0f),
            long = getFloat(UpdateLocationWorker.LONG_PROGRESS_KEY, 0f),
        )
    }
}