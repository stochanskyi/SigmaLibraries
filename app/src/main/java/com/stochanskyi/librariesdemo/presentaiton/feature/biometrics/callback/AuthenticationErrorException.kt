package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.callback

class AuthenticationErrorException(
    private val errorCode: Int,
    private val errString: CharSequence
) : Exception(errString.toString())