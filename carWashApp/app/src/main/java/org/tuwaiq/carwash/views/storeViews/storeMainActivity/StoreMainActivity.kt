package org.tuwaiq.carwash.views.storeViews.storeMainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserHomeFragment
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMoreFragment
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserOrdersFragment

class StoreMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        //link bottom navigation view
        val bNavigationView = findViewById<BottomNavigationView>(R.id.storebottomNavigationView)

        // navigate fragments through navigation item selected
        bNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.storeHomeMenu -> {
                    // set stores frag
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.storefragmentContainerView, StoreHomeFragment()).commit()
                    true
                }
                R.id.storePreviewMenu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.storefragmentContainerView, StorePreviewFragment()).commit()
                    true
                }
                R.id.storeMoreMenu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.storefragmentContainerView, StoreMoreFragment()).commit()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.UserfragmentContainerView, StoreMoreFragment()).commit()
                    false
                }

            }
        }

    }
}