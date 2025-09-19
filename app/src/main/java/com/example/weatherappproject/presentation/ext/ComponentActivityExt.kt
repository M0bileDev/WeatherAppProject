package com.example.weatherappproject.presentation.ext

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

fun ComponentActivity.configPermissionLauncher(
    onSuccess: () -> Unit,
    onError: () -> Unit
): ActivityResultLauncher<Array<String>> = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
) { permissions ->
    val permissionsGranted = permissions.all { it.value }
    if (permissionsGranted) {
        onSuccess()
    } else {
        onError()
    }
}