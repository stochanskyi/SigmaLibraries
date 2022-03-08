package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.transformers

import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.type.ActivityTypeTransformer
import javax.inject.Inject

class ActivityTypeNameTransformer @Inject constructor() : ActivityTypeTransformer<Int> {
    override fun transformInVehicle(): Int {
        return R.string.activity_type_in_vehicle
    }

    override fun transformOnBicycle(): Int {
        return R.string.activity_type_on_bicycle
    }

    override fun transformOnFoot(): Int {
        return R.string.activity_type_on_foot
    }

    override fun transformStill(): Int {
        return R.string.activity_type_still
    }

    override fun transformUnknown(): Int {
        return R.string.activity_type_unknown
    }

    override fun transformTilting(): Int {
        return R.string.activity_type_tilting
    }

    override fun transformWalking(): Int {
        return R.string.activity_type_walking
    }

    override fun transformRunning(): Int {
        return R.string.activity_type_running
    }
}