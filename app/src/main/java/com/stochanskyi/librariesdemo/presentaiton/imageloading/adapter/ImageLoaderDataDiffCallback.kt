package com.stochanskyi.librariesdemo.presentaiton.imageloading.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stochanskyi.librariesdemo.presentaiton.imageloading.models.ImageLoaderViewData

class ImageLoaderDataDiffCallback(
    private val oldList: List<ImageLoaderViewData>,
    private val newList: List<ImageLoaderViewData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}