package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityCancelAppointmentBinding
import org.tuwaiq.carwash.model.Order

class CancelAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCancelAppointmentBinding
    private val viewModel : UserOrdersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancelAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val order = intent.getSerializableExtra("order") as Order
        // link views
        val mToolBar = binding.mytoolbar
        val btnPrevious = binding.buttonBack
        val btnCancel = binding.buttonCancel

        setSupportActionBar(mToolBar)

        btnPrevious.setOnClickListener { finish() }

        btnCancel.setOnClickListener {
            viewModel.cancelAppointment(order._id)
            viewModel.deletedAppointment.observe(this){
                Log.d("Deleted?", "appointment: $it")
                finish()
            }
        }
    }
}