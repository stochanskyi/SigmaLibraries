package com.stochanskyi.librariesdemo.presentaiton.activityrecognition.activityupdate

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class ActivityUpdateFragment : Fragment(R.layout.fragment_activity_update) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ActivityUpdateViewModel by viewModels { viewModelFactory }


}