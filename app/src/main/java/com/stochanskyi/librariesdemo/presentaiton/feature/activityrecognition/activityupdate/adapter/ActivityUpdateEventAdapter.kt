package com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData

class ActivityUpdateEventAdapter : ListAdapter<ActivityUpdateEventViewData, ActivityUpdateEventViewHolder>(
    ActivityUpdateEventItemCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityUpdateEventViewHolder {
        return ActivityUpdateEventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ActivityUpdateEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}