package com.stochanskyi.librariesdemo.domain.features.activityrecognition

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import kotlinx.coroutines.flow.Flow

interface ActivityRecognitionRepository {
    fun getActivityUpdateFlow(): Flow<ActivityUpdate>
    fun stopActivityUpdate()
}