package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stochanskyi.librariesdemo.presentaiton.utils.livedata.SingleLiveEvent
import javax.crypto.Cipher
import javax.inject.Inject

class BiometricsDemoViewModel @Inject constructor() : ViewModel() {

    private val _restoredLiveData = MutableLiveData<String>()
    val restoredLiveData: LiveData<String> = _restoredLiveData

    private val _authenticateEncryptCipherLiveData = SingleLiveEvent<Cipher>()
    val authenticateEncryptCipherLiveData: LiveData<Cipher> = _authenticateEncryptCipherLiveData

    private val _authenticateDecryptCipherLiveData = SingleLiveEvent<Cipher>()
    val authenticateDecryptCipherLiveData: LiveData<Cipher> = _authenticateDecryptCipherLiveData

    fun saveData(data: String) {

    }

    fun restoreData() {

    }

    fun encodeData(cipher: Cipher) {

    }

    fun decodeData(cipher: Cipher) {

    }
}