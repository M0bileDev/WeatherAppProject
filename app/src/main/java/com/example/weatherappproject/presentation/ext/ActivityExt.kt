package com.example.weatherappproject.presentation.ext

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher

fun ActivityResultLauncher<Array<String>>.runLocationsPermissions() = launch(
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
)

fun Activity.startApplicationSettings() =
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        val uri = Uri.fromParts(
            "package",
            packageName, null
        )
        setData(uri)
        startActivity(this)
    }