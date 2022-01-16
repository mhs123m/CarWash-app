package org.tuwaiq.carwash.views.userViews.userMainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.UserHomeFragment
import org.tuwaiq.carwash.views.userViews.userMainActivity.moreFragment.UserMoreFragment
import org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.UserOrdersFragment

class UserMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)

        //link bottom navigation and tool bar
        val bNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavViewUser)
        val mainToolBar = findViewById<Toolbar>(R.id.mainToolBar)
        // set tool bar
        setSupportActionBar(mainToolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // get instance of fragments
        val userHomeFragment = UserHomeFragment()
        val userOrdersFragment = UserOrdersFragment()
        val userMoreFragment = UserMoreFragment()

        // navigate fragments through navigation item selected
        bNavigationView.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                R.id.CarWashStores -> {
                    // set stores frag
                    fragment = userHomeFragment

                }
                R.id.orders -> {
                    fragment = userOrdersFragment

                }
                R.id.more -> {
                    fragment = userMoreFragment

                }
                else -> {
                    fragment = userHomeFragment

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
                .replace(R.id.UserfragmentContainerView, fragment)
                .commit()
            return true
        }
        return false
    }
}