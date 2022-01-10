package org.tuwaiq.carwash.views.userViews.userLoginAndRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.carwash.R


class UserSignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        // on create, set UserLogin fragment
        supportFragmentManager.beginTransaction().add(R.id.mFrameLayout, UserLoginFragment())
            .commit()

    }
}