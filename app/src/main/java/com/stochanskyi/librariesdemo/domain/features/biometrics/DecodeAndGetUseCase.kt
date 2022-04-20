package com.stochanskyi.librariesdemo.domain.features.biometrics

import com.stochanskyi.librariesdemo.data.repository.biometrics.BiometricsDemoRepository
import javax.inject.Inject

class DecodeAndGetUseCase @Inject constructor(
    private val repository: BiometricsDemoRepository
) {
    suspend operator fun invoke(authenticator: CipherAuthenticator): String? {
        val cipher = repository.getDataDecryptCipher() ?: return null

        val authenticatedCipher = authenticator.authenticate(cipher)
        return repository.getDecryptedData(authenticatedCipher)
    }
}