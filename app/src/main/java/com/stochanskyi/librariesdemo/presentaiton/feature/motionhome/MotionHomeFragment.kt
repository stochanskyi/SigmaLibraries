package com.stochanskyi.librariesdemo.presentaiton.feature.motionhome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentMotionHomeBinding

class MotionHomeFragment : Fragment(R.layout.fragment_motion_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentMotionHomeBinding.bind(view).run {
            initViews(this)
        }
    }

    private fun initViews(binding: FragmentMotionHomeBinding) {
        binding.collapsingToolbarButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_motion_collapsing)
        }
        binding.musicPlayerButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_music_player)
        }
    }

}