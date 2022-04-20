package com.stochanskyi.librariesdemo.data.biometrics

import android.security.keystore.KeyProperties

object DemoCryptoConfig {
    const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    const val ALGORITHM_MODE = KeyProperties.BLOCK_MODE_GCM
    const val ALGORITHM_PADDING = KeyProperties.ENCRYPTION_PADDING_NONE

    const val CIPHER_TRANSFORMATION = "$ALGORITHM/$ALGORITHM_MODE/$ALGORITHM_PADDING"

    const val KEY_LENGTH = 256
    const val KEY_TAG_LENGTH = 128
    const val KEY_PURPOSE = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT

    const val KEYSTORE_NAME = "AndroidKeyStore"

    const val KEYSTORE_ALIAS = "demo_data_alias"

}