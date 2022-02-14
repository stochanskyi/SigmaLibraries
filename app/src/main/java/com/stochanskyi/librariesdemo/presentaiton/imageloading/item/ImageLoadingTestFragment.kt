package com.stochanskyi.librariesdemo.presentaiton.imageloading.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.FragmentImageLoadingTestBinding

class ImageLoadingTestFragment : Fragment(R.layout.fragment_image_loading_test) {

    companion object {
        private const val IMAGE_LOADER_ID_KEY = "image_loader_id_key"

        fun newInstance(imageLoaderId: Int) = ImageLoadingTestFragment().apply {
            arguments = Bundle().apply {
                putInt(IMAGE_LOADER_ID_KEY, imageLoaderId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentImageLoadingTestBinding.bind(view).run {
            initViews(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentImageLoadingTestBinding) {
        val loaderId = arguments?.getInt(IMAGE_LOADER_ID_KEY) ?: return

        binding.testText.text = loaderId.toString()
    }

    private fun initObservers(binding: FragmentImageLoadingTestBinding) {
        //TODO
    }
}