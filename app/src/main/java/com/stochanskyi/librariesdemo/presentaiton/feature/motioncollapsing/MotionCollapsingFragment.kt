package com.stochanskyi.librariesdemo.presentaiton.feature.motioncollapsing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentMotionCollapsingBinding
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter.ActivityUpdateEventAdapter
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData
import kotlin.random.Random

class MotionCollapsingFragment : Fragment(R.layout.fragment_motion_collapsing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentMotionCollapsingBinding.bind(view).run {
            initViews(this)
        }
    }

    private fun initViews(binding: FragmentMotionCollapsingBinding) {
        val data = List(50) {
            ActivityUpdateEventViewData(
                Random.nextInt(),
                R.string.activity_type_running,
                100
            )
        }
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = ActivityUpdateEventAdapter().apply {
            submitList(data)
        }
    }
}