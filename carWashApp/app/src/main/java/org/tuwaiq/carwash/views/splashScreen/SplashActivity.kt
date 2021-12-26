package org.tuwaiq.carwash.views.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Globals.sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        var handler = Handler()

        handler.postDelayed({
            if (Globals.sharedPreferences.getString("Token", null) != null &&
                Globals.sharedPreferences.getBoolean("IsUser",false)
            ) {
                startActivity(Intent(this, UserMainActivity::class.java))
                finish()
            } else if (Globals.sharedPreferences.getString("Token", null) != null &&
                Globals.sharedPreferences.getBoolean("IsStore",false)) {
                startActivity(Intent(this, StoreMainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, UserSignInActivity::class.java))
                finish()
            }

        }, 300)
    }
}