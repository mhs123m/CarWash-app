package org.tuwaiq.carwash.views.userViews.userLoginAndRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.utils.LocationHelperFunctions


class UserSignInActivity : AppCompatActivity() {
    private lateinit var locationHelperFunctions: LocationHelperFunctions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        // on create, set UserLogin fragment
        supportFragmentManager.beginTransaction().add(R.id.mFrameLayout, UserLoginFragment())
            .commit()
        locationHelperFunctions = LocationHelperFunctions.getInstance()
        // get location permission
        getLastKnownLocation()
    }

    private fun getLastKnownLocation(): LatLng {
        val lastKnownLocation = locationHelperFunctions.getLastKnownLocation(this)
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }
}