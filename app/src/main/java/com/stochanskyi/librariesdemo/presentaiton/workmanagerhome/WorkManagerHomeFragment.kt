package com.stochanskyi.librariesdemo.presentaiton.workmanagerhome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentWorkHomeManagerBinding

class WorkManagerHomeFragment : Fragment(R.layout.fragment_work_home_manager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentWorkHomeManagerBinding.bind(view).run {
            initListeners(this)
        }
    }

    private fun initListeners(binding: FragmentWorkHomeManagerBinding) = with(binding) {
        locationUpdateButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_location_update)
        }
    }
}