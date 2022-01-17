package org.tuwaiq.carwash.views.storeViews.storeMainActivity


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.util.LocationHelperFunctions
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeHomeFragment.StoreHomeFragment
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.StoreMoreFragment
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.StorePreviewFragment


class StoreMainActivity : AppCompatActivity() {
    private lateinit var locationHelperFunctions: LocationHelperFunctions
    private lateinit var navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)
        locationHelperFunctions = LocationHelperFunctions.getInstance()

        // get location permission
        getLastKnownLocation()

        // retrieve saved data to share pref
        Globals.sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        //link bottom navigation view
        navigation = findViewById(R.id.storebottomNavigationView)


        // get instance of fragments
        val storeHomeFragment =  StoreHomeFragment()
        val storePreviewFragment =  StorePreviewFragment()
        val storeMoreFragment =  StoreMoreFragment()
        // navigate fragments through navigation item selected
        navigation.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                R.id.storeHomeMenu -> {
                    fragment = storeHomeFragment


                }
                R.id.storePreviewMenu -> {
                    fragment = storePreviewFragment

                }
                R.id.storeMoreMenu -> {
                    fragment = storeMoreFragment

                }
                else -> {
                    fragment = storeMoreFragment

                }
            }

            loadFragment(fragment)

        }

    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack("aa")
                .replace(R.id.storefragmentContainerView, fragment)
                .commit()
            return true
        }
        return false
    }
    private fun getLastKnownLocation(): LatLng {
        val lastKnownLocation = locationHelperFunctions.getLastKnownLocation(this)
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }
}