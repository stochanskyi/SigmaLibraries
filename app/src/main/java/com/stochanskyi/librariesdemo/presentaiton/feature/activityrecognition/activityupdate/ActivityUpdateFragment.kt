package com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentActivityUpdateBinding
import com.stochanskyi.librariesdemo.presentaiton.feature.activityrecognition.activityupdate.adapter.ActivityUpdateEventAdapter
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import com.stochanskyi.librariesdemo.utils.hasActivityRecognitionPermission
import com.stochanskyi.librariesdemo.utils.registerPermissionLauncher
import com.stochanskyi.librariesdemo.utils.requestActivityRecognitionPermission
import javax.inject.Inject

class ActivityUpdateFragment : Fragment(R.layout.fragment_activity_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ActivityUpdateViewModel by viewModels { viewModelFactory }

    private val activityRecognitionPermissionLauncher = registerPermissionLauncher(
        onGranted = {
            viewModel.startOrStopUpdate()
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentActivityUpdateBinding.bind(view).run {
            initViews(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentActivityUpdateBinding) = with(binding) {
        eventsRecyclerView.adapter = ActivityUpdateEventAdapter()
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        eventsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        startStopButton.setOnClickListener {
            startActivityUpdates()
        }
    }

    private fun startActivityUpdates() {
        if (hasActivityRecognitionPermission()) {
            viewModel.startOrStopUpdate()
            return
        }
        activityRecognitionPermissionLauncher.requestActivityRecognitionPermission()
    }

    private fun initObservers(binding: FragmentActivityUpdateBinding) {
        viewModel.activityEventsLiveData.observe(viewLifecycleOwner) {
            (binding.eventsRecyclerView.adapter as? ActivityUpdateEventAdapter)?.submitList(it)
        }
        viewModel.isServiceRunningLiveData.observe(viewLifecycleOwner) {
            val buttonTextRes = if (it) {
                R.string.activity_update_stop
            } else {
                R.string.activity_update_start
            }

            binding.startStopButton.text = getString(buttonTextRes)
        }
    }

}
