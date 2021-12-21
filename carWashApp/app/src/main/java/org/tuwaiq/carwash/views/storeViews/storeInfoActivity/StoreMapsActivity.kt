package org.tuwaiq.carwash.views.storeViews.storeInfoActivity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityStoreMapsBinding

class StoreMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var mapMarker: Marker? = null
    var currentLocation: Location? = null
//    lateinit var latLng: LatLng
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStoreMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoreMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission dialog
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 11
            )
            return
        }
        val task = fusedLocationProviderClient?.lastLocation
        task?.addOnSuccessListener { location ->
            this.currentLocation = location
            Log.d("Location", "$currentLocation")

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            11 -> if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                fetchLocation()
            }
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (currentLocation != null) {
            drawMarker(LatLng(currentLocation!!.latitude, currentLocation!!.longitude))
        } else {
            // set marker in riyadh if location not granted.
            val riyadh = LatLng(24.7136, 46.6753)
            drawMarker(riyadh)
        }

        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(p0: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
                if (mapMarker != null) {
                    mapMarker?.remove()
                }
                val newLatLng = LatLng(marker.position.latitude, marker.position.longitude)
                drawMarker(newLatLng)
            }

            override fun onMarkerDragStart(p0: Marker) {
            }

        })

        // Add a marker in Sydney and move the camera
//
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun drawMarker(latLng: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions().draggable(true)
        markerOptions.position(latLng)
        markerOptions.title("Store Location")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f))
    }

}