package com.stochanskyi.librariesdemo.data.biometrics

import javax.crypto.Cipher

interface CipherDataSource {
    fun getEncryptCipher(): Cipher
    fun getDecryptCipher(initializationVector: ByteArray): Cipher
}