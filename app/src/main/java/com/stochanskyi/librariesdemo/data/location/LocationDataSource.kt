package com.stochanskyi.librariesdemo.data.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await

interface LocationDataSource {

    suspend fun getCurrentLocation(): Location

    suspend fun getLastLocation(): Location

}

@SuppressLint("MissingPermission")
class LocationDataSourceImpl(
    private val locationClient: FusedLocationProviderClient,
    private val priority: Int
) : LocationDataSource {

    override suspend fun getCurrentLocation(): Location {
        return locationClient.getCurrentLocation(
            priority, createCancellationToken()
        ).await()
    }

    override suspend fun getLastLocation(): Location {
        return locationClient.lastLocation.await()
    }

    private fun createCancellationToken() = CancellationTokenSource().token

}