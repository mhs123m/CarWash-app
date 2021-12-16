package org.tuwaiq.carwash.views.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Globals.sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        var handler = Handler()

        handler.postDelayed({


            val i = Intent(this, UserSignInActivity::class.java)
            startActivity(i)
            finish()
        }, 300)
    }
}