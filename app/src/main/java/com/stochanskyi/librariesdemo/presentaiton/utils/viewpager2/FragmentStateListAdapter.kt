package com.stochanskyi.librariesdemo.presentaiton.utils.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stochanskyi.librariesdemo.ext.clearAndAdd

typealias DiffCallbackCreator<T> = (oldList: List<T>, newList: List<T>) -> DiffUtil.Callback

abstract class FragmentStateListAdapter<T> : FragmentStateAdapter {

    private val data: MutableList<T> = mutableListOf()

    protected abstract val diffCallbackCreator: DiffCallbackCreator<T>

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): T = data[position]

    fun getItemOrNull(position: Int): T? = data.getOrNull(position)

    fun submitList(newData: List<T>) {
        val callback = diffCallbackCreator(data, newData)
        val result = DiffUtil.calculateDiff(callback)

        data.clearAndAdd(newData)
        result.dispatchUpdatesTo(this)
    }



}