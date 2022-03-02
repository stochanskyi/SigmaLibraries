package com.stochanskyi.librariesdemo.data.repository.activityrecognition.models

import com.stochanskyi.librariesdemo.data.repository.activityrecognition.models.type.ActivityType

class UserActivity(
    val activityType: ActivityType,
    val confidence: Int
)
