package org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment.storeProfileActivity

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityStoreMapsBinding
import org.tuwaiq.carwash.utils.LocationHelperFunctions
import java.io.IOException
import java.util.*

class StoreMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    // declare a global variable of FusedLocationProviderClient
//    var fusedLocationClient: FusedLocationProviderClient? = null

    private lateinit var storeLocationMarker: Marker
    private lateinit var locationHelperFunctions: LocationHelperFunctions
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStoreMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationHelperFunctions = LocationHelperFunctions.getInstance()

//        //set tool bar
        val toolbar = binding.mapToolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.back_btn_icon_toolbar)
        toolbar.setNavigationOnClickListener {
            ifLocationFinish()
        }


        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // on confirm -> finish
        binding.buttonConfirmStoreLocation.setOnClickListener {
            ifLocationFinish()
        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        drawMarker(getLastKnownLocation())

        onLongPressMap(mMap)

    }

    // search view
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.map_toolbar_menu, menu)
        val search = menu.findItem(R.id.mapSearchView)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                // on below line we are getting the
                // location name from search view.
                val location: String = searchView.query.toString()

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

                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun ifLocationFinish() {
        if (locationHelperFunctions.isGeometrySet()) {
            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("No location selected")
                .setIcon(R.drawable.map_icon_10)
                .setMessage("please Long Press on map, to pick location")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("Later") { _, _ ->
                    finish()
                }
                .create()
                .show()
        }
    }




    private fun getLastKnownLocation(): LatLng {
        val lastKnownLocation = locationHelperFunctions.getLastKnownLocation(this)
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }

    private fun drawMarker(latLng: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions().position(latLng).title("Store Location")
            .snippet(
                locationHelperFunctions.getAddress(
                    latLng.latitude, latLng.longitude, this
                )
            )

        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        mMap.uiSettings.isZoomControlsEnabled = true
        storeLocationMarker = mMap.addMarker(markerOptions)!!
    }

    private fun onLongPressMap(map: GoogleMap) {
        map.setOnMapLongClickListener {
            drawMarker(it)
            locationHelperFunctions.getAddress(it.latitude, it.longitude, this)
            locationHelperFunctions.setGeometry(it, this)
        }
    }

}