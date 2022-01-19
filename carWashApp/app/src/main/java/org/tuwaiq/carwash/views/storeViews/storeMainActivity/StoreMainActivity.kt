package org.tuwaiq.carwash.views.storeViews.storeMainActivity


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.utils.LocationHelperFunctions
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.StoreHomeFragment
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment.StoreMoreFragment
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.servicesFragment.StorePreviewFragment


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

        //link bottom navigation view and tool bar
        navigation = findViewById(R.id.storebottomNavigationView)
        val mainStoreToolBar = findViewById<Toolbar>(R.id.mainStoreToolBar)
        // set tool bar
        setSupportActionBar(mainStoreToolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


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