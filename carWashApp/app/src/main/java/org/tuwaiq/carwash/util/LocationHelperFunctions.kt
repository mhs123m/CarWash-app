package org.tuwaiq.carwash.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.model.LatLng
import org.tuwaiq.carwash.model.Coordinates
import org.tuwaiq.carwash.model.Geometry
import java.util.*

class LocationHelperFunctions {


    // Companion object to allow for static access to the class
    companion object {
        private var locationService: LocationHelperFunctions? = null
        private var geometry = Geometry()

        var lastKnownLocation: LatLng? = null
//            set(value) {
//                field = value
//                if (field != null) {
//                    if (value != null) {
//                        geometry.coordinates = Coordinates(value.longitude, value.latitude)
//                    }
//                }
//            }

        fun getInstance(): LocationHelperFunctions {
            if (locationService == null) {
                locationService = LocationHelperFunctions()
            }
            return locationService!!
        }
    }


    fun getLastKnownLocation(activity: Activity): LatLng {
        checkLocationPermission(activity)

        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var location = LatLng(0.0, 0.0)
        val providers = locationManager.getProviders(true)
        var bestLocation: Location? = null

        for (provider in providers) {
            val l = locationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                // Found best last known location
                bestLocation = l
            }
        }

        bestLocation?.let {
            val latLng = LatLng(it.latitude, it.longitude)
            location = latLng
            lastKnownLocation = location
        }

        return location
    }


    // Check if user granted fine location permission, if not request it
    private fun checkLocationPermission(activity: Activity) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }

    }

    fun getAddress(lat: Double, lng: Double, context: Context): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lng, 1)
        return "${addresses[0].adminArea}, ${addresses[0].countryName}"
    }

    fun setGeometry(latLng: LatLng, context: Context) {
        geometry = Geometry(
            listOf(latLng.longitude,latLng.latitude),
            getAddress(latLng.latitude, latLng.longitude, context)
        )
    }

    fun getGeometry() = geometry

}