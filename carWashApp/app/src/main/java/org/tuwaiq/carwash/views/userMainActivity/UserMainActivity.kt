package org.tuwaiq.carwash.views.userMainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.tuwaiq.carwash.R

class UserMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)

        //link bottom navigation view
        val bNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavViewUser)
        // navigate fragments through navigation item selected
        bNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.CarWashStores -> {
                    // set stores frag
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.UserfragmentContainerView,UserHomeFragment()).commit()
                    true
                }
                R.id.orders -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.UserfragmentContainerView,UserOrdersFragment()).commit()
                    true
                }
                R.id.more -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.UserfragmentContainerView,UserMoreFragment()).commit()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.UserfragmentContainerView, UserHomeFragment()).commit()
                    false
                }

            }
        }

    }
}