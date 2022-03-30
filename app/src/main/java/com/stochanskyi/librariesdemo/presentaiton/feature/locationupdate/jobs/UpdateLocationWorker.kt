package com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.domain.features.location.GetCurrentLocationUseCase
import kotlinx.coroutines.delay
import javax.inject.Inject

class UpdateLocationWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    @Inject
    lateinit var getCurrentLocationUseCase: GetCurrentLocationUseCase

    private val notificationManager by lazy {
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        appContext.appComponent().inject(this)
    }

    override suspend fun doWork(): Result {
        while (!isStopped) {
            val location = getCurrentLocationUseCase()
            val notificationContent = applicationContext.getString(R.string.long_lat_format, location.long, location.lat)

            setForeground(createForegroundInfo(notificationContent))
            delay(20000)
        }
        return Result.success()
    }

    private fun createForegroundInfo(content: String): ForegroundInfo {
        return ForegroundInfo(12, createNotification(content))
    }

    private fun createNotification(content: String): Notification {
        initChannel()
        return NotificationCompat.Builder(applicationContext, "location_update")
            .setContentTitle("Test")
            .setContentTitle(content)
            .setSmallIcon(R.drawable.ic_splash_logo)
            .build()
    }

    private fun initChannel() {
        val channel = NotificationChannel(
            "location_update",
            "Location update",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}