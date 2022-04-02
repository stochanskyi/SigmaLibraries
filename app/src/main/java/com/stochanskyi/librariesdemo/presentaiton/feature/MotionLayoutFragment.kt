package com.stochanskyi.librariesdemo.presentaiton.feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentMotionLayoutBinding
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter.ActivityUpdateEventAdapter
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.viewdata.ActivityUpdateEventViewData
import kotlin.random.Random

class MotionLayoutFragment : Fragment(R.layout.fragment_motion_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentMotionLayoutBinding.bind(view).run {
            initViews(this)
        }
    }

    private fun initViews(binding: FragmentMotionLayoutBinding) {
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