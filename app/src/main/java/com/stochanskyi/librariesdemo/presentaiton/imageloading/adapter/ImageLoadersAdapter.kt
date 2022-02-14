package com.stochanskyi.librariesdemo.presentaiton.imageloading.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.stochanskyi.librariesdemo.presentaiton.imageloading.item.ImageLoadingTestFragment
import com.stochanskyi.librariesdemo.presentaiton.imageloading.models.ImageLoaderViewData
import com.stochanskyi.librariesdemo.presentaiton.utils.viewpager2.DiffCallbackCreator
import com.stochanskyi.librariesdemo.presentaiton.utils.viewpager2.FragmentStateListAdapter

class ImageLoadersAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateListAdapter<ImageLoaderViewData>(
    fragmentManager,
    lifecycle,
) {

    override val diffCallbackCreator: DiffCallbackCreator<ImageLoaderViewData> =
        { oldList, newList -> ImageLoaderDataDiffCallback(oldList, newList) }

    override fun createFragment(position: Int): Fragment = ImageLoadingTestFragment.newInstance(getItem(position).id)

}