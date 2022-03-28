package com.stochanskyi.librariesdemo.presentaiton.locationupdate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentLocationUpdateBinding
import com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate.ActivityUpdateViewModel
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class LocationUpdateFragment: Fragment(R.layout.fragment_location_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ActivityUpdateViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentLocationUpdateBinding.bind(view).run {
            initListeners(this)
        }
    }

    private fun initListeners(binding: FragmentLocationUpdateBinding) = with(binding) {
        startStopButton.setOnClickListener { viewModel.startOrStopUpdate() }
    }
}