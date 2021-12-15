package org.tuwaiq.carwash.views.storeViews.storeLoginAndRegister

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.Globals

class StoreSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_sign_in)

        // retrive saved data to share pref

        Globals.sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

                // on create, set StoreLogin fragment
        supportFragmentManager.beginTransaction().add(R.id.storeFrameLayout, StoreLogInFragment())
            .commit()
    }
}