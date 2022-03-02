package com.stochanskyi.librariesdemo.domain.features.activityrecognition.models

class ActivityUpdate(
    val userActivity: List<UserActivity>,
    val mostConfident: UserActivity
)