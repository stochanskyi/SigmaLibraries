package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentBiometricsDemoBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class BiometricsDemoFragment : Fragment(R.layout.fragment_biometrics_demo) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: BiometricsDemoViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentBiometricsDemoBinding.bind(view).run {
            initViews(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentBiometricsDemoBinding): Unit = with(binding) {
        saveButton.setOnClickListener {
            viewModel.saveData(dataEditText.text?.toString() ?: "")
        }
        restoreButton.setOnClickListener {
            viewModel.restoreData()
        }
    }

    private fun initObservers(binding: FragmentBiometricsDemoBinding) = with(binding) {
        viewModel.restoredLiveData.observe(viewLifecycleOwner) {
            dataEditText.setText(it)
        }
    }
}