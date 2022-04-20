package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentBiometricsDemoBinding
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import javax.crypto.Cipher
import javax.inject.Inject

class BiometricsDemoFragment : Fragment(R.layout.fragment_biometrics_demo) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: BiometricsDemoViewModel by viewModels { viewModelFactory }

    private val biometricManager: BiometricManager by lazy { BiometricManager.from(requireContext()) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentBiometricsDemoBinding.bind(view).run {
            initViews(this)
            initObservers(this)
        }
    }

    private fun initViews(binding: FragmentBiometricsDemoBinding): Unit = with(binding) {
        saveButton.setOnClickListener {
            viewModel.saveData(dataEditText.text?.toString() ?: "")
        }
        restoreButton.setOnClickListener {
            viewModel.restoreData()
        }
    }

    private fun initObservers(binding: FragmentBiometricsDemoBinding) = with(binding) {
        viewModel.restoredLiveData.observe(viewLifecycleOwner) {
            dataEditText.setText(it)
        }
        viewModel.authenticateEncryptCipherLiveData.observe(viewLifecycleOwner) { cipher ->
            showEncryptCipherBiometricPrompt(cipher)
        }
        viewModel.authenticateDecryptCipherLiveData.observe(viewLifecycleOwner) { cipher ->
            showDecodeCipherBiometricPrompt(cipher)
        }
    }

    private fun showEncryptCipherBiometricPrompt(cipher: Cipher) {
        showBiometricPrompt(cipher) { authenticatedCipher ->
            authenticatedCipher?.let { viewModel.encodeData(it) }
        }
    }

    private fun showDecodeCipherBiometricPrompt(cipher: Cipher) {
        showBiometricPrompt(cipher) { authenticatedCipher ->
            authenticatedCipher?.let { viewModel.decodeData(it) }
        }
    }

    private inline fun showBiometricPrompt(
        cipher: Cipher,
        crossinline successAction: (Cipher?) -> Unit
    ) {
        createBiometricPrompt(successAction)
            .authenticate(
                createBiometricPromptInfo("", ""),
                BiometricPrompt.CryptoObject(cipher)
            )
    }

    private inline fun createBiometricPrompt(crossinline successAction: (Cipher?) -> Unit): BiometricPrompt {
        return BiometricPrompt(this, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                successAction(result.cryptoObject?.cipher)
            }
        })
    }

    private fun createBiometricPromptInfo(
        title: String,
        description: String
    ): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder().apply {
            setTitle("Authenticate to restore saved data")
            setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
        }.build()
    }
}