package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.data

import kotlinx.coroutines.CancellableContinuation
import javax.crypto.Cipher

class CipherAuthenticationData(
    val cipher: Cipher,
    val continuation: CancellableContinuation<Cipher>
)