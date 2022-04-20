package com.stochanskyi.librariesdemo.data.biometrics

import android.security.keystore.KeyGenParameterSpec
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class DemoCipherDataSource @Inject constructor(
    private val keyStore: KeyStore,
    private val keyGenerator: KeyGenerator
) : CipherDataSource {

    override fun getEncryptCipher(): Cipher {
        return getCipher().apply {
            init(Cipher.ENCRYPT_MODE, getOrCreateSecretKey())
        }
    }

    override fun getDecryptCipher(initializationVector: ByteArray): Cipher {
        return getCipher().apply {
            init(
                Cipher.DECRYPT_MODE,
                getOrCreateSecretKey(),
                GCMParameterSpec(DemoCryptoConfig.KEY_TAG_LENGTH, initializationVector)
            )
        }
    }

    private fun getCipher(): Cipher {
        return Cipher.getInstance(DemoCryptoConfig.CIPHER_TRANSFORMATION)
    }

    private fun getOrCreateSecretKey(): SecretKey {
        return getSecretKey() ?: createSecretKey()
    }

    private fun getSecretKey(): SecretKey? {
        keyStore.load(null)
        return keyStore.getKey(DemoCryptoConfig.KEYSTORE_ALIAS, null) as? SecretKey
    }

    private fun createSecretKey(): SecretKey {
        val specs = createKeyGenParameterSpec()
        keyGenerator.init(specs)

        return keyGenerator.generateKey()
    }

    private fun createKeyGenParameterSpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            DemoCryptoConfig.KEYSTORE_ALIAS,
            DemoCryptoConfig.KEY_PURPOSE
        ).apply {
            setKeySize(DemoCryptoConfig.KEY_LENGTH)
            setBlockModes(DemoCryptoConfig.ALGORITHM_MODE)
            setEncryptionPaddings(DemoCryptoConfig.ALGORITHM_PADDING)
            setUserAuthenticationRequired(true)
//            setUserAuthenticationParameters()
        }.build()
    }

}