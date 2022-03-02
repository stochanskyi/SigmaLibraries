package com.stochanskyi.librariesdemo.presentaiton.activityrecognition

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentActivityRecognitionBinding
import com.stochanskyi.librariesdemo.databinding.FragmentActivityUpdateBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class ActivityRecognitionFragment : Fragment(R.layout.fragment_activity_recognition) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentActivityRecognitionBinding.bind(view).run {
            initListeners(this)
        }

    }

    private fun initListeners(binding: FragmentActivityRecognitionBinding) {
        binding.updateButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_activity_update)
        }
    }

}