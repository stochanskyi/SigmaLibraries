package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.callback

import androidx.biometric.BiometricPrompt
import kotlinx.coroutines.CancellableContinuation
import javax.crypto.Cipher
import kotlin.coroutines.resumeWithException

class AuthenticationCipherCallback(
    private val continuation: CancellableContinuation<Cipher>
) : BiometricPrompt.AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        if (!continuation.isActive) return
        continuation.resumeWithException(AuthenticationErrorException(errorCode, errString))
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        if (!continuation.isActive) return

        result.cryptoObject?.cipher?.let {
            continuation.resumeWith(Result.success(it))
        } ?: run {
            continuation.resumeWith(Result.failure(AuthenticationFailedException()))
        }
    }

    override fun onAuthenticationFailed() {
        continuation.resumeWithException(AuthenticationFailedException())
    }
}