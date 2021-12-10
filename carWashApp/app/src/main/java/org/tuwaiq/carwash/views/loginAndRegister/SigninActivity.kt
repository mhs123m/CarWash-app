package org.tuwaiq.carwash.views.loginAndRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.tuwaiq.carwash.R

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // on create, set UserLogin fragment
        supportFragmentManager.beginTransaction().add(R.id.mFrameLayout, UserLoginFragment())
            .commit()




    }

}