package com.stochanskyi.librariesdemo.data.repository.activityrecognition.models.type

interface ActivityTypeTransformer<T> {

    fun transformInVehicle(): T

    fun transformOnBicycle(): T

    fun transformOnFoot(): T

    fun transformStill(): T

    fun transformUnknown(): T

    fun transformTilting(): T

    fun transformWalking(): T

    fun transformRunning(): T
}