package com.example.weatherappproject.framework.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weatherappproject.data.location.DefaultLocationTracker
import com.example.weatherappproject.data.location.LocationData
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTrackerImpl @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val application: Application
) : DefaultLocationTracker {

    private fun hasLocationPermissions(): Boolean = with(application) {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return@with hasAccessFineLocationPermission || hasAccessCoarseLocationPermission
    }

    private fun hasGpsEnabled(): Boolean = with(application) {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )

        return@with isGpsEnabled
    }


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LocationData? {
        if (!hasLocationPermissions() || !hasGpsEnabled()) {
            return null
        }

        // convert callback to cancellable, suspendable coroutine
        return suspendCancellableCoroutine { continuation ->
            client.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        continuation.resume(
                            LocationData(result.latitude, result.longitude)
                        )
                    } else {
                        continuation.resume(null)
                    }

                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    continuation.resume(
                        LocationData(result.latitude, result.longitude)
                    )
                }

                addOnFailureListener {
                    continuation.resume(null)
                }

                addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }
}

