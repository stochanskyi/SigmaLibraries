package com.stochanskyi.librariesdemo.domain.features.activityrecognition

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.DisposableFlow

interface ActivityRecognitionRepository {
    suspend fun getActivityUpdateFlow(interval: Long): Result<DisposableFlow<ActivityUpdate>>
}