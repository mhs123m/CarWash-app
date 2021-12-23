package org.tuwaiq.carwash.views.storeViews.storeInfoActivity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
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
import java.io.IOException
import java.util.*

class StoreMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    // declare a global variable of FusedLocationProviderClient
//    var fusedLocationClient: FusedLocationProviderClient? = null
    var mapMarker: Marker? = null

    //    var currentLocation: Location? = null
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStoreMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoreMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // on confirm -> finish
        binding.buttonConfirmStoreLocation.setOnClickListener {
            finish()
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are getting the
                // location name from search view.
                val location: String = binding.searchView.query.toString()

                // below line is to create a list of address
                // where we will store the list of all address.
                var addressList: List<Address>? = null

                // checking if the entered location is null or not.
                if (location != null || location == "") {
                    // on below line we are creating and initializing a geo coder.
                    val geocoder = Geocoder(this@StoreMapsActivity)
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    val address: Address = addressList!![0]

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    val latLng = LatLng(address.latitude, address.longitude)

                    // on below line we are adding marker to that position.
                    mMap.addMarker(MarkerOptions().position(latLng).title(location))

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        getLastKnownLocation()

    }

//    private fun getLastKnownLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // request permission dialog
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ), 11
//            )
//
//            return
//        }
//
////        fusedLocationClient!!.lastLocation
////            .addOnSuccessListener {
////                   // currentLocation = it
////
////
////                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
////                val mapFragment = supportFragmentManager
////                    .findFragmentById(R.id.map) as SupportMapFragment
////                mapFragment.getMapAsync(this)
////
////                Log.d("LOCATION", "$currentLocation")
////
////            }
//
//    }

    /*
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
      }

     */

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            11 -> if (grantResults.isNotEmpty() &&
//                grantResults[0] == PackageManager.PERMISSION_GRANTED
//            ) {
//
//                getLastKnownLocation()
//            }
//        }
//
//    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        if (currentLocation != null) {
//            drawMarker(LatLng(currentLocation?.latitude!!, currentLocation?.longitude!!))
//        }
//        else{
        // set marker in riyadh if location not granted.
        val riyadh = LatLng(24.7136, 46.6753)
        drawMarker(riyadh)

//        if (currentLocation == null) {
//
//            return
//        }

        onLongPressMap(mMap)

        // Add a marker in Sydney and move the camera
//
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun drawMarker(latLng: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions().position(latLng).title("Store Location")
            .snippet(getAddress(latLng.latitude, latLng.longitude))

        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        mMap.uiSettings.isZoomControlsEnabled = true
        mapMarker = mMap.addMarker(markerOptions)
    }

    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lng, 1)
        return addresses[0].getAddressLine(0).toString()
    }

    private fun onLongPressMap(map: GoogleMap) {
        map.setOnMapLongClickListener {
            drawMarker(it)
            mapMarker// update location in data base from here TODO
        }
    }

}