package com.stochanskyi.librariesdemo.data.activityrecognition

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.ActivityRecognitionResult
import com.stochanskyi.librariesdemo.app.appComponent
import javax.inject.Inject

class ActivityUpdateReceiver : BroadcastReceiver() {

    @Inject
    lateinit var consumer: ActivityUpdateConsumer

    override fun onReceive(context: Context, intent: Intent?) {
        context.appComponent().inject(this)
        intent ?: return

        val activityData = ActivityRecognitionResult.extractResult(intent)

        consumer.consumeActivityRecognitionResult(activityData)
    }

}