package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.viewdata

import androidx.annotation.StringRes

data class ActivityUpdateEventViewData(
    val id: Int,
    @StringRes val activityNameRes: Int,
    val activityConfidence: Int
)