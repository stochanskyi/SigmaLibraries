package com.stochanskyi.librariesdemo.data.repository.activityrecognition

import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSource
import com.stochanskyi.librariesdemo.data.repository.activityrecognition.models.ActivityUpdate
import com.stochanskyi.librariesdemo.data.repository.activityrecognition.models.UserActivity
import com.stochanskyi.librariesdemo.data.repository.activityrecognition.models.type.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface ActivityRecognitionRepository {
    fun getActivityUpdateFlow(): Flow<ActivityUpdate>
    fun stopActivityUpdate()
}

class ActivityRecognitionRepositoryImpl @Inject constructor(
    private val activityRecognitionDataSource: ActivityRecognitionDataSource
) : ActivityRecognitionRepository {
    override fun getActivityUpdateFlow(): Flow<ActivityUpdate> {
        return activityRecognitionDataSource.getActivityUpdates().map { it.parse() }
    }

    override fun stopActivityUpdate() {
        activityRecognitionDataSource.stopActivityUpdates()
    }

}

private fun ActivityRecognitionResult.parse() = ActivityUpdate(
    userActivity = probableActivities.map { it.parse() },
    mostConfident = mostProbableActivity.parse()
)

private fun DetectedActivity.parse(): UserActivity {
    return UserActivity(
        activityType = type.parseType(),
        confidence = confidence
    )
}

private fun Int.parseType(): ActivityType = when (this) {
    DetectedActivity.IN_VEHICLE -> ActivityTypeInVehicle()
    DetectedActivity.ON_BICYCLE -> ActivityOnBicycle()
    DetectedActivity.ON_FOOT -> ActivityOnFoot()
    DetectedActivity.STILL -> ActivityStill()
    DetectedActivity.TILTING -> ActivityTilting()
    DetectedActivity.WALKING -> ActivityWalking()
    DetectedActivity.RUNNING -> ActivityRunning()
    else -> ActivityUnknown()
}