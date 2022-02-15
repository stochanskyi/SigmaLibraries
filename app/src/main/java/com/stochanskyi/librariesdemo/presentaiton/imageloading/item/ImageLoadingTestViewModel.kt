package com.stochanskyi.librariesdemo.presentaiton.imageloading.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.presentaiton.data.imagelaoding.provider.ImageLoadersProvider
import com.stochanskyi.librariesdemo.presentaiton.utils.imageloading.ImageLoader
import javax.inject.Inject

class ImageLoadingTestViewModel @Inject constructor(
    private val imageLoadersProvider: ImageLoadersProvider
) : ViewModel() {

    private val _imageLoaderLiveData = MutableLiveData<ImageLoader>()
    val imageLoaderLiveData: LiveData<ImageLoader> = _imageLoaderLiveData

    private val _imageUrl = MutableLiveData("https://cdn.wallpapersafari.com/40/74/h3fByJ.jpg")
    val imageUrl: LiveData<String> = _imageUrl

    fun setup(loaderId: Int) {
        _imageLoaderLiveData.value = imageLoadersProvider.getLoaderOrDefault(loaderId).imageLoader
    }

}