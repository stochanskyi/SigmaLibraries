package com.stochanskyi.librariesdemo.presentaiton.imageloading.item

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentImageLoadingTestBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.loadImageAndMeasure
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageLoadingTestFragment : Fragment(R.layout.fragment_image_loading_test) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ImageLoadingTestViewModel by viewModels { viewModelFactory }

    private var imageLoader: ImageLoader? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentImageLoadingTestBinding.bind(view).run {
            initArgs()
            initObservers(this)
        }
    }

    private fun initArgs() {
        arguments?.getInt(IMAGE_LOADER_ID_KEY)?.let {
            viewModel.setup(it)
        }
    }

    private fun initObservers(binding: FragmentImageLoadingTestBinding) {
        viewModel.imageLoaderLiveData.observe(viewLifecycleOwner) { loader ->
            imageLoader = loader
        }
        viewModel.imageUrl.observe(viewLifecycleOwner) { imageUrl ->
            loadImage(binding, imageUrl)
        }
    }

    private fun loadImage(binding: FragmentImageLoadingTestBinding, imageUrl: String) {
        //TODO
        val loader = imageLoader ?: return
        lifecycleScope.launch {
            val time = binding.imageView.loadImageAndMeasure(imageUrl, loader) {
                placeholderRes = R.drawable.ic_launcher_foreground
            }

            binding.timeTextView.text = time.toString()
        }
    }

    companion object {
        private const val IMAGE_LOADER_ID_KEY = "image_loader_id_key"

        fun newInstance(imageLoaderId: Int) = ImageLoadingTestFragment().apply {
            arguments = Bundle().apply {
                putInt(IMAGE_LOADER_ID_KEY, imageLoaderId)
            }
        }
    }
}