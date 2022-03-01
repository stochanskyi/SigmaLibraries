package com.stochanskyi.librariesdemo.presentaiton.activityrecognition

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class ActivityRecognitionFragment : Fragment(R.layout.fragment_activity_recognition) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ActivityRecognitionViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }
}