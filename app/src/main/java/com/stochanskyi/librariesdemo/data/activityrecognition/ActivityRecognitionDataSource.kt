package com.stochanskyi.librariesdemo.data.activityrecognition

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.ActivityRecognitionClient
import com.google.android.gms.location.ActivityRecognitionResult
import com.stochanskyi.librariesdemo.data.activityrecognition.exceptions.ActivityRecognitionLaunchFailedException
import com.stochanskyi.librariesdemo.data.activityrecognition.models.ActivityUpdatesRequestData
import com.stochanskyi.librariesdemo.utils.intentMutableFlag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.lang.Long.min
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface ActivityRecognitionDataSource {
    suspend fun getActivityUpdates(interval: Long): Result<ActivityUpdatesRequestData>

    suspend fun stopActivityUpdates(requestId: Int): Boolean
}

@OptIn(FlowPreview::class)
@Singleton
class ActivityRecognitionDataSourceImpl @Inject constructor(
    private val context: Context,
    private val activityRecognitionClient: ActivityRecognitionClient,
) : ActivityRecognitionDataSource, ActivityUpdateConsumer {

    private val flow: MutableSharedFlow<ActivityRecognitionResult> = MutableSharedFlow()

    private val requestsIntervals: MutableMap<Int, Long> = HashMap()

    private var isRecognitionRunning: Boolean = false
    private var lastRequestId: Int = 0

    //TODO provide context outside
    private val scope = CoroutineScope(SupervisorJob())

    private val mutex = Mutex()

    init {
        controlActivityUpdatesWithActiveSubscriber()
    }

    override suspend fun getActivityUpdates(
        interval: Long
    ): Result<ActivityUpdatesRequestData> = mutex.withLock {
        if (!tryLaunchActivityUpdates(interval)) {
            return Result.failure(ActivityRecognitionLaunchFailedException())
        }

        lastRequestId++

        requestsIntervals[lastRequestId] = interval

        val requestFlow = flow
            .takeWhile { isRequestRunning(lastRequestId) }
            .sample(interval)

        return Result.success(
            ActivityUpdatesRequestData(
                id = lastRequestId,
                flow = requestFlow
            )
        )
    }

    override suspend fun stopActivityUpdates(requestId: Int): Boolean = mutex.withLock {
        val requestInterval = requestsIntervals[requestId] ?: return false
        removeRequestWith(requestId)
        val minInterval = resolveMinInterval(requestInterval)

        if (requestInterval >= minInterval) {
            return true
        }

        return tryStopActivityUpdates() && tryLaunchActivityUpdates()
    }

    override fun consumeActivityRecognitionResult(result: ActivityRecognitionResult?) {
        result ?: return

        scope.launch { flow.emit(result) }
    }

    private suspend fun tryLaunchActivityUpdates(): Boolean {
        val interval = minInterval() ?: return false
        return tryLaunchActivityUpdates(interval)
    }

    private suspend inline fun tryLaunchActivityUpdates(interval: Long): Boolean {
        val minInterval = resolveMinInterval(interval)

        if (minInterval <= interval && isRecognitionRunning) {
            return true
        }

        return tryStopActivityUpdates() && tryStartActivityUpdates(minInterval)
    }

    private suspend fun tryStopActivityUpdates(): Boolean {
        if (!isRecognitionRunning) return true

        return requireStopActivityUpdates().also {
            isRecognitionRunning = false
        }
    }

    private suspend fun tryStartActivityUpdates(interval: Long): Boolean {
        if (isRecognitionRunning) return true

        return requireStartActivityUpdates(interval).also {
            isRecognitionRunning = true
        }
    }

    private suspend fun requireStopActivityUpdates(): Boolean = suspendCoroutine { continuation ->
        activityRecognitionClient.removeActivityUpdates(createPendingIntent())
            .addOnSuccessListener {
                continuation.resume(true)
            }
            .addOnFailureListener {
                continuation.resume(false)
            }
    }

    private suspend fun requireStartActivityUpdates(
        interval: Long
    ): Boolean = suspendCoroutine { continuation ->
        activityRecognitionClient.requestActivityUpdates(interval, createPendingIntent())
            .addOnSuccessListener {
                continuation.resume(true)
            }
            .addOnFailureListener {
                continuation.resume(false)
            }
    }

    private fun resolveMinInterval(interval: Long): Long {
        val lastMinInterval = minInterval() ?: return interval
        return min(interval, lastMinInterval)
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(context, ActivityUpdateReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, intentMutableFlag())
    }

    private fun removeRequestWith(id: Int) {
        requestsIntervals.remove(id)
    }

    private fun isRequestRunning(id: Int): Boolean {
        return requestsIntervals.contains(id)
    }

    private fun minInterval(): Long? {
        return requestsIntervals.minByOrNull { it.value }?.value
    }

    private fun controlActivityUpdatesWithActiveSubscriber() {
        flow.subscriptionCount
            .map { it > 0 }
            .distinctUntilChanged()
            .onEach { hasCollectors ->
                mutex.withLock {
                    if (hasCollectors) tryLaunchActivityUpdates()
                    else tryStopActivityUpdates()
                }
            }
            .launchIn(scope)
    }
}