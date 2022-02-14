package com.stochanskyi.librariesdemo.presentaiton.imageloading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.ext.clearAndAdd
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.definitions.ImageLoaderDefinition
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.provider.ImageLoadersProvider
import com.stochanskyi.librariesdemo.presentaiton.imageloading.models.ImageLoaderViewData
import javax.inject.Inject

class ImageLoadersViewModel @Inject constructor(
    imageLoadersProvider: ImageLoadersProvider
) : ViewModel() {

    private val imageLoaderDefinitions: MutableList<ImageLoaderDefinition> = mutableListOf()

    private val _imageLoadersLiveData: MutableLiveData<List<ImageLoaderViewData>> =
        MutableLiveData()

    val imageLoadersLiveData: LiveData<List<ImageLoaderViewData>> = _imageLoadersLiveData

    init {
        imageLoaderDefinitions.clearAndAdd(imageLoadersProvider.getLoaders())
        _imageLoadersLiveData.value = imageLoaderDefinitions.toViewData()
    }
}

private fun List<ImageLoaderDefinition>.toViewData(): List<ImageLoaderViewData> {
    return map { it.toViewData() }
}

private fun ImageLoaderDefinition.toViewData(): ImageLoaderViewData {
    return ImageLoaderViewData(
        id = id,
        nameRes = nameRes
    )
}