package com.stochanskyi.librariesdemo.data.repository.activityrecognition

import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity
import com.stochanskyi.librariesdemo.data.activityrecognition.ActivityRecognitionDataSource
import com.stochanskyi.librariesdemo.data.activityrecognition.models.ActivityUpdatesRequestData
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.UserActivity
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.type.*
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.ActivityRecognitionRepository
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.DisposableFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal typealias ActivityUpdatesFlow = DisposableFlow<ActivityUpdate>

class ActivityRecognitionRepositoryImpl @Inject constructor(
    private val activityRecognitionDataSource: ActivityRecognitionDataSource
) : ActivityRecognitionRepository {
    override suspend fun getActivityUpdateFlow(interval: Long): Result<ActivityUpdatesFlow> {
        return activityRecognitionDataSource.getActivityUpdates(interval).map {
            it.parse()
        }
    }

    private fun ActivityUpdatesRequestData.parse() = DisposableFlow(
        flow.map { it.parse() }
    ) {
        activityRecognitionDataSource.stopActivityUpdates(id)
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
}