package com.stochanskyi.librariesdemo.data.activityrecognition

import com.google.android.gms.location.ActivityRecognitionResult

interface ActivityUpdateConsumer {
    fun consumeActivityRecognitionResult(result: ActivityRecognitionResult?)
}