package com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentLocationUpdateBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class LocationUpdateFragment: Fragment(R.layout.fragment_location_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LocationUpdateViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentLocationUpdateBinding.bind(view).run {
            initListeners(this)
            initObservers(this)
        }
    }

    private fun initObservers(binding: FragmentLocationUpdateBinding) {
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            binding.currentLocationTextView.text =
                getString(R.string.long_lat_format, it.long, it.lat)
        }
        viewModel.isUpdatingRunningLiveData.observe(viewLifecycleOwner) {
            val buttonTextRes = if (it) {
                R.string.activity_update_stop
            } else {
                R.string.activity_update_start
            }
            binding.startStopButton.text = getString(buttonTextRes)
        }
    }

    private fun initListeners(binding: FragmentLocationUpdateBinding) = with(binding) {
        startStopButton.setOnClickListener { viewModel.startOrStopUpdates() }
    }
}