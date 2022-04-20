package com.stochanskyi.librariesdemo.domain.features.biometrics

import javax.crypto.Cipher

fun interface CipherAuthenticator {
    suspend fun authenticate(cipher: Cipher): Cipher
}