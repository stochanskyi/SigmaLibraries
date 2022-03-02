package com.stochanskyi.librariesdemo.data.repository.activityrecognition.models

class ActivityUpdate(
    val userActivity: List<UserActivity>,
    val mostConfident: UserActivity
)