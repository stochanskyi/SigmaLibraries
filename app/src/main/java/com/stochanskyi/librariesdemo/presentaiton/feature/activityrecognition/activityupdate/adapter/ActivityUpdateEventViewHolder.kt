package com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.ViewHolderActivityUpdateEventBinding
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData
import com.stochanskyi.librariesdemo.presentaiton.utils.view.context
import com.stochanskyi.librariesdemo.presentaiton.utils.view.inflater

class ActivityUpdateEventViewHolder private constructor(
    private val binding: ViewHolderActivityUpdateEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ActivityUpdateEventViewHolder(
            ViewHolderActivityUpdateEventBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(data: ActivityUpdateEventViewData) = with(binding) {
        nameTextView.text = context.getString(data.activityNameRes)
        confidenceTextView.text = context.getString(R.string.activity_update_event_confidence, data.activityConfidence)
    }

}