package com.qualentum.sprint4.presentation.common.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class ManageLocation @Inject constructor() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getUserLocation(context: Context): Location? {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val isUserLocationPermissionsGranted = true
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if(!isGPSEnabled || !isUserLocationPermissionsGranted) {
            return null
        }

        return suspendCancellableCoroutine {cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(result) {}
                    } else {
                        cont.resume(null) {}
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it) {}
                }
                addOnCanceledListener {
                    cont.resume(null) {}
                }
                addOnFailureListener {
                    cont.resume(null) {}
                }
            }
        }
    }
}