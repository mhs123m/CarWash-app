package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.step4AppointmentBooked

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityAppointmentBookedBinding

class AppointmentBookedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBookedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBookedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}