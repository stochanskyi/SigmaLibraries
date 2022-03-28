package com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData

class ActivityUpdateEventItemCallback : DiffUtil.ItemCallback<ActivityUpdateEventViewData>() {

    override fun areItemsTheSame(
        oldItem: ActivityUpdateEventViewData,
        newItem: ActivityUpdateEventViewData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ActivityUpdateEventViewData,
        newItem: ActivityUpdateEventViewData
    ): Boolean {
        return oldItem == newItem
    }
}