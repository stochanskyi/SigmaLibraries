package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ObserveActivityUpdateUseCase
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.DisposableFlow
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.transformers.ActivityTypeNameTransformer
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ACTIVITY_UPDATES_INTERVAL = 3000L

class ActivityUpdateViewModel @Inject constructor(
    private val observeActivityUpdateUseCase: ObserveActivityUpdateUseCase,
    private val actionTypeNameTransformer: ActivityTypeNameTransformer
) : ViewModel() {

    private val _isServiceRunningLiveData = MutableLiveData<Boolean>(false)
    val isServiceRunningLiveData: LiveData<Boolean> = _isServiceRunningLiveData

    private val _activityEventsLiveData = MutableLiveData<List<ActivityUpdateEventViewData>>()
    val activityEventsLiveData: LiveData<List<ActivityUpdateEventViewData>> =
        _activityEventsLiveData

    private var activityEvents: List<ActivityUpdateEventViewData> = listOf()

    private var updatesFlow: DisposableFlow<ActivityUpdate>? = null

    private var collectingJob: Job? = null

    private var lastId: Int = 0

    override fun onCleared() {
        viewModelScope.launch {
            stopUpdate()
        }
    }

    fun startOrStopUpdate() {
        if (collectingJob?.isActive == true) {
            stopUpdate()
        } else {
            startUpdate()
        }
    }

    private fun startUpdate() {
        collectingJob = viewModelScope.launch {
            val result = observeActivityUpdateUseCase(ACTIVITY_UPDATES_INTERVAL)

            result.onFailure {
                return@launch
            }

            _isServiceRunningLiveData.postValue(true)
            updatesFlow = result.getOrNull()

            updatesFlow?.collect {
                addNewEvent(it)
                Log.d("AC_REC_TAG", it.mostConfident.activityType.toString())
            }
        }
    }

    private fun stopUpdate() {
        collectingJob?.cancel()
        viewModelScope.launch {
            updatesFlow?.dispose()
            updatesFlow = null
            _isServiceRunningLiveData.postValue(false)
        }
    }

    private fun addNewEvent(data: ActivityUpdate) {
        activityEvents = data.asViewData() + activityEvents
        _activityEventsLiveData.value = activityEvents
    }

    private fun ActivityUpdate.asViewData(): ActivityUpdateEventViewData {
        lastId++
        return ActivityUpdateEventViewData(
            lastId,
            mostConfident.activityType.transform(actionTypeNameTransformer),
            mostConfident.confidence
        )
    }
}

private operator fun <T> T.plus(list: List<T>): List<T> {
    val result = ArrayList<T>(list.size + 1)
    result.add(this)
    result.addAll(list)
    return result
}
