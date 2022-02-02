package com.stochanskyi.librariesdemo.presentaiton.simplecall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimpleCallViewModel @Inject constructor(
    private val simpleCallUseCase: SimpleCallUseCase
) : ViewModel() {

    private val _callResultLiveData: MutableLiveData<String> = MutableLiveData()
    val callResultLiveData: LiveData<String> = _callResultLiveData

    private val _requestLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val requestLoadingLiveData: LiveData<Boolean> = _requestLoadingLiveData

    init {
        viewModelScope.launch {
            _requestLoadingLiveData.value = true

            withContext(Dispatchers.IO) {
                _callResultLiveData.postValue(simpleCallUseCase.invoke())
            }

            _requestLoadingLiveData.value = false
        }
    }
}