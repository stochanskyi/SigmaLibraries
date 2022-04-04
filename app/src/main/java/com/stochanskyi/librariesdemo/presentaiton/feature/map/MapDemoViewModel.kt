package com.stochanskyi.librariesdemo.presentaiton.feature.map

import androidx.annotation.ColorRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.domain.features.maps.GetCountriesBoundsUseCase
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.CountryBound
import com.stochanskyi.librariesdemo.presentaiton.feature.map.models.CountryBoundViewData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MapDemoViewModel(
    private val getCountriesBoundsUseCase: GetCountriesBoundsUseCase,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _countryBoundsLiveData = MutableLiveData<List<CountryBoundViewData>>()
    val countryBoundsLiveData: LiveData<List<CountryBoundViewData>> = _countryBoundsLiveData

    init {
        viewModelScope.launch(ioDispatcher) {
            val bounds = getCountriesBoundsUseCase()

            _countryBoundsLiveData.postValue(bounds.parse())
        }
    }

    private fun List<CountryBound>.parse(): List<CountryBoundViewData> = map {
        it.parse()
    }

    private fun CountryBound.parse(): CountryBoundViewData {
        return CountryBoundViewData(
            getColorId(countryCode),
            geometry
        )
    }

    @ColorRes
    private fun getColorId(countryCode: String): Int {
        // TODO some smart logic
        return R.color.country_map_color
    }

}