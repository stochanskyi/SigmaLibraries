package com.stochanskyi.librariesdemo.presentaiton.simplecall

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentSimpleCallBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class SimpleCallFragment : Fragment(R.layout.fragment_simple_call) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val simpleCallViewModel: SimpleCallViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentSimpleCallBinding.bind(view).run {
            initObservers(this)
        }
    }

    private fun initObservers(binding: FragmentSimpleCallBinding) {
        simpleCallViewModel.requestLoadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
        simpleCallViewModel.callResultLiveData.observe(viewLifecycleOwner) {
            binding.resultTextView.text = it
        }
    }

    companion object {
        fun newInstance(): SimpleCallFragment {
            return SimpleCallFragment()
        }
    }

}