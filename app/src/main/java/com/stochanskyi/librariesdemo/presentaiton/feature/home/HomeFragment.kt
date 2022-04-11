package com.stochanskyi.librariesdemo.presentaiton.feature.home

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentHomeBinding.bind(view).run {
            initListeners(this)
        }
    }

    private fun initListeners(binding: FragmentHomeBinding) = with(binding) {
        simpleCallButton.setOnClickListener {
            navigateToAction(R.id.fragment_simple_call)
        }
        imageLoadingButton.setOnClickListener {
            navigateToAction(R.id.fragment_image_loaders)
        }
        activityRecognitionButton.setOnClickListener {
            navigateToAction(R.id.fragment_activity_recognition)
        }
        workManagerButton.setOnClickListener { 
            navigateToAction(R.id.fragment_work_manager_home)
        }
        motionLayoutButton.setOnClickListener {
            navigateToAction(R.id.fragment_motion_home)
        }
        googleMapsButton.setOnClickListener {
            navigateToAction(R.id.fragment_map_demo)
        }
        coordinatorLayoutButton.setOnClickListener {
            navigateToAction(R.id.fragment_coordinator_demo)
        }
    }
    
    private fun navigateToAction(@IdRes action: Int) {
        findNavController().navigate(action)
    }

}