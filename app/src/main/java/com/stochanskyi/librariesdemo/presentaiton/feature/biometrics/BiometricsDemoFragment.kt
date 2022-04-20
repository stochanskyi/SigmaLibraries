package com.stochanskyi.librariesdemo.presentaiton.feature.biometrics

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.app.appComponent
import com.stochanskyi.librariesdemo.databinding.FragmentBiometricsDemoBinding
import com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.callback.AuthenticationCipherCallback
import com.stochanskyi.librariesdemo.presentaiton.feature.biometrics.data.CipherAuthenticationData
import com.stochanskyi.librariesdemo.presentaiton.utils.ViewModelFactory
import kotlinx.coroutines.CancellableContinuation
import javax.crypto.Cipher
import javax.inject.Inject

class BiometricsDemoFragment : Fragment(R.layout.fragment_biometrics_demo) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: BiometricsDemoViewModel by viewModels { viewModelFactory }

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
            restoredTextView.text = it
        }
        viewModel.authenticateCipherLiveData.observe(viewLifecycleOwner) { data ->
            showBiometricPrompt(data ?: return@observe)
        }
    }

    private fun showBiometricPrompt(
        data: CipherAuthenticationData
    ) {
        createBiometricPrompt(data.continuation).authenticate(
            createBiometricPromptInfo(),
            BiometricPrompt.CryptoObject(data.cipher)
        )
    }

    private fun createBiometricPrompt(continuation: CancellableContinuation<Cipher>): BiometricPrompt {
        return BiometricPrompt(this, AuthenticationCipherCallback(continuation))
    }

    private fun createBiometricPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder().apply {
            setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
        }.build()
    }
}