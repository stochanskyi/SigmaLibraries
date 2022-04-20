package com.stochanskyi.librariesdemo.domain.features.biometrics

import com.stochanskyi.librariesdemo.data.repository.biometrics.BiometricsDemoRepository
import javax.inject.Inject

class EncryptAndSaveUseCase @Inject constructor(
    private val repository: BiometricsDemoRepository
) {
    suspend operator fun invoke(data: String, authenticator: CipherAuthenticator) {
        val cipher = repository.getDataEncryptCipher()
        val authenticatedCipher = authenticator.authenticate(cipher)

        repository.saveEncryptedData(data, authenticatedCipher)
    }
}