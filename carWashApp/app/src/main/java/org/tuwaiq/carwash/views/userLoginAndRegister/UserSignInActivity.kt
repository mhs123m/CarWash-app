package org.tuwaiq.carwash.views.userLoginAndRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import org.tuwaiq.carwash.R
import android.app.Activity




class UserSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        // on create, set UserLogin fragment
        supportFragmentManager.beginTransaction().add(R.id.mFrameLayout, UserLoginFragment())
            .commit()

    }
}