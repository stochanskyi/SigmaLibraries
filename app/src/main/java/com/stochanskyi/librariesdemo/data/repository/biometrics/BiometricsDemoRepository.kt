package com.stochanskyi.librariesdemo.data.repository.biometrics

import com.stochanskyi.librariesdemo.data.biometrics.CipherDataSource
import com.stochanskyi.librariesdemo.data.biometrics.storage.DemoDataDto
import com.stochanskyi.librariesdemo.data.biometrics.storage.DemoDataStorage
import javax.crypto.Cipher
import javax.inject.Inject

interface BiometricsDemoRepository {

    fun getDataEncryptCipher(): Cipher
    fun getDataDecryptCipher(): Cipher?

    fun getDecryptedData(cipher: Cipher): String?
    fun saveEncryptedData(string: String, cipher: Cipher)
}

class BiometricsDemoRepositoryImpl @Inject constructor(
    private val cipherDataSource: CipherDataSource,
    private val demoDataStorage: DemoDataStorage
) : BiometricsDemoRepository {

    override fun getDataEncryptCipher(): Cipher {
        return cipherDataSource.getEncryptCipher()
    }

    override fun getDataDecryptCipher(): Cipher? {
        val initializationVector = demoDataStorage.getData()?.initVector ?: return null
        return cipherDataSource.getDecryptCipher(initializationVector)
    }

    override fun getDecryptedData(cipher: Cipher): String? {
        val encodedData = demoDataStorage.getData() ?: return null
        val decryptedData = cipher.doFinal(encodedData.data)

        return String(decryptedData)
    }

    override fun saveEncryptedData(string: String, cipher: Cipher) {
        val encryptedData = cipher.doFinal(string.toByteArray())
        demoDataStorage.setData(DemoDataDto(
            data = encryptedData,
            initVector = cipher.iv
        ))
    }

}