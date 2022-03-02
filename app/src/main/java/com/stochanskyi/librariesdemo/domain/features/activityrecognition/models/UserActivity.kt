package com.stochanskyi.librariesdemo.domain.features.activityrecognition.models

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.type.ActivityType

class UserActivity(
    val activityType: ActivityType,
    val confidence: Int
)
