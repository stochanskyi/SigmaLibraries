package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentActivityUpdateBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class ActivityUpdateFragment : Fragment(R.layout.fragment_activity_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ActivityUpdateViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentActivityUpdateBinding.bind(view).run {
            startStopButton.setOnClickListener {
                viewModel.startOrStopUpdate()
            }
        }
    }

}