package com.stochanskyi.librariesdemo.data.activityrecognition.models

import com.google.android.gms.location.ActivityRecognitionResult
import kotlinx.coroutines.flow.Flow

class ActivityUpdatesRequestData(
    val id: Int,
    val flow: Flow<ActivityRecognitionResult>,
)