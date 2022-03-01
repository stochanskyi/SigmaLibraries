package com.stochanskyi.librariesdemo.presentaiton.home

import android.os.Bundle
import android.view.View
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
            findNavController().navigate(R.id.fragment_simple_call)
        }
        imageLoadingButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_image_loaders)
        }
        activityRecognitionButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_activity_recognition)
        }
    }

}