package com.stochanskyi.librariesdemo.data.activityrecognition

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import com.google.android.gms.location.ActivityRecognitionResult
import com.stochanskyi.librariesdemo.utils.intentMutableFlag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

private const val DEFAULT_UPDATE_TIMEOUT_MILLIS = 5000L

interface ActivityRecognitionDataSource {
    fun getActivityUpdates(timeout: Long = DEFAULT_UPDATE_TIMEOUT_MILLIS): Flow<ActivityRecognitionResult>

    fun stopActivityUpdates(id: Int)
}

@Singleton
class ActivityRecognitionDataSourceImpl @Inject constructor(
    private val context: Context
) : ActivityRecognitionDataSource, ActivityUpdateConsumer {

    private var flow: MutableSharedFlow<ActivityRecognitionResult> = MutableSharedFlow()

    private val activityRecognitionClient: ActivityRecognitionClient
        get() = ActivityRecognition.getClient(context)

    private val pendingIntent: PendingIntent = createPendingIntent()

    private val scope = CoroutineScope(SupervisorJob())

    private val isRecognitionRunning = AtomicBoolean(false)

    override fun getActivityUpdates(timeout: Long): Flow<ActivityRecognitionResult> {
        if (isRecognitionRunning.compareAndSet(false, true)) {
            launchActivityUpdates()
        }

        return flow
    }

    private fun launchActivityUpdates() {
        activityRecognitionClient.requestActivityUpdates(0, pendingIntent)
            .addOnSuccessListener {
                isRecognitionRunning.compareAndSet(true, false)
            }
    }

    override fun stopActivityUpdates(id: Int) {
        if (isRecognitionRunning.compareAndSet(true, false)) {
            activityRecognitionClient.removeActivityUpdates(pendingIntent)
        }
    }

    override fun consumeActivityRecognitionResult(result: ActivityRecognitionResult?) {
        result ?: return

        scope.launch { flow.emit(result) }
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, ActivityUpdateReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, intentMutableFlag())
    }

}