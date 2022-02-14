package com.stochanskyi.librariesdemo.presentaiton.imageloading

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentImageLoadersBinding
import com.stochanskyi.librariesdemo.presentaiton.imageloading.adapter.ImageLoadersAdapter
import com.stochanskyi.librariesdemo.presentaiton.imageloading.models.ImageLoaderViewData
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.inject.Inject

class ImageLoadersFragment : Fragment(R.layout.fragment_image_loaders) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ImageLoadersViewModel by viewModels { viewModelFactory }

    private var tabMediator: TabLayoutMediator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentImageLoadersBinding.bind(view).run {
            initViews(this)
            initObservers(this)
        }
    }

    private fun initObservers(binding: FragmentImageLoadersBinding) {
        viewModel.imageLoadersLiveData.observe(viewLifecycleOwner) { loaders ->
            onNewLoaders(binding, loaders)
        }
    }

    private fun initViews(binding: FragmentImageLoadersBinding) = binding.run {
        initViewPager(binding)
    }

    private fun initViewPager(binding: FragmentImageLoadersBinding) = binding.run {
        viewPager.isUserInputEnabled = false
        viewPager.adapter = ImageLoadersAdapter(childFragmentManager, lifecycle)
        invalidateTabMediator()
    }

    private fun invalidateTabMediator() {
        tabMediator?.detach()
        tabMediator?.attach()
    }

    private fun onNewLoaders(binding: FragmentImageLoadersBinding, loaders: List<ImageLoaderViewData>) {
        tabMediator?.detach()
        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = context?.getString(loaders[position].nameRes)
        }.apply { attach() }

        binding.viewPager.adapterAction { submitList(loaders) }
    }

    private fun ViewPager2.adapterAction(action: ImageLoadersAdapter.() -> Unit) {
        (adapter as? ImageLoadersAdapter)?.action()
    }

    companion object {
        fun newInstance() = ImageLoadersFragment()
    }
}