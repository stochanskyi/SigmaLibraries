package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stochanskyi.librariesdemo.domain.features.biometrics.DecodeAndGetUseCase
import com.stochanskyi.librariesdemo.domain.features.biometrics.EncryptAndSaveUseCase
import com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.data.CipherAuthenticationData
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.crypto.Cipher
import javax.inject.Inject

class BiometricsDemoViewModel @Inject constructor(
    private val encryptAndSaveUseCase: EncryptAndSaveUseCase,
    private val decodeAndGetUseCase: DecodeAndGetUseCase
) : ViewModel() {

    private val _restoredLiveData = MutableLiveData<String>()
    val restoredLiveData: LiveData<String> = _restoredLiveData

    private val _authenticateCipherLiveData = MutableLiveData<CipherAuthenticationData?>()
    val authenticateCipherLiveData: LiveData<CipherAuthenticationData?> =
        _authenticateCipherLiveData

    fun saveData(data: String) {
        viewModelScope.launch {
            runCatching {
                encryptAndSaveUseCase(data, ::authenticateCipher)
            }
        }
    }

    private suspend fun authenticateCipher(cipher: Cipher): Cipher {
        return suspendCancellableCoroutine<Cipher> { continuation ->
            _authenticateCipherLiveData.value = CipherAuthenticationData(
                cipher, continuation
            )
        }.also {
            _authenticateCipherLiveData.value = null
        }
    }

    fun restoreData() {
        viewModelScope.launch {
            runCatching {
                decodeAndGetUseCase(::authenticateCipher)
            }.onSuccess {
                updateData(it ?: return@launch)
            }
        }
    }

    private fun updateData(data: String) {
        _restoredLiveData.value = data
    }
}