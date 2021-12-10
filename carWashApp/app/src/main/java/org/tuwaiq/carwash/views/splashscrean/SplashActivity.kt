package org.tuwaiq.carwash.views.splashscrean

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.loginAndRegister.SigninActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()

        handler.postDelayed({


            val i = Intent(this, SigninActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}