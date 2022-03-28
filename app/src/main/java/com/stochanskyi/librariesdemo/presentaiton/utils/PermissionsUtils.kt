package com.stochanskyi.librariesdemo.presentaiton.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline fun Fragment.registerPermissionLauncher(
    crossinline onGranted: () -> Unit = {},
    crossinline onDenied: () -> Unit = {}
): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onGranted()
        } else {
            onDenied()
        }
    }
}

fun Fragment.hasActivityRecognitionPermission(): Boolean {
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        return true
    }

    return hasPermissions(Manifest.permission.ACTIVITY_RECOGNITION)
}

fun ActivityResultLauncher<String>.requestActivityRecognitionPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        launch(Manifest.permission.ACTIVITY_RECOGNITION)
    }
}

fun Fragment.hasPermissions(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(requireContext(), permission) ==
        PackageManager.PERMISSION_GRANTED
}