package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.reschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityAddServiceBinding.bind
import org.tuwaiq.carwash.databinding.ActivityAddServiceBinding.inflate
import org.tuwaiq.carwash.databinding.ActivityRescheduleDetailsBinding
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.UserOrdersViewModel

class RescheduleDetailsActivity : AppCompatActivity() {
    private val viewModel: UserOrdersViewModel by viewModels()
    private lateinit var binding: ActivityRescheduleDetailsBinding
    private lateinit var order: Order
    private lateinit var appointment: Appointment
    private lateinit var tvStoreName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRescheduleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order = intent.getSerializableExtra("order") as Order

        // link views
        initializeViews()
        setViewsWithData()

        binding.textViewBack.setOnClickListener { finish() }
        binding.imageViewBackBtnn.setOnClickListener { finish() }

        binding.textViewConfirm.setOnClickListener { rescheduleAppointment(appointment) }
    }

    private fun initializeViews() {
        tvStoreName = binding.textViewStoreNameConfirm
        tvDateAndTime = binding.textViewDateTimeConfirm
        tvServiceTitle = binding.textViewServiceTitleConfirm
        tvServicePrice = binding.textViewServicePriceConfirm
    }


    private fun setViewsWithData() {
        val day = appointment.day.day
        val time = TimeSlotsHelperFunctions.convertIndexToTime(appointment.day.slot.index)
        order.storeId?.let {
            tvStoreName.text = it.name
        }
        tvDateAndTime.text =
            "$day | $time"
        order?.let {
            tvServiceTitle.text = it.serviceId.title
            tvServicePrice.text = it.serviceId.price.toString()
        }
    }

    private fun rescheduleAppointment(appointment: Appointment) {
        viewModel.updateAppointment(order._id, appointment)
        viewModel.updatedAppointment.observe(this) {
            val intent = Intent(this, AppointmentRescheduledActivity::class.java)
            intent.putExtra("order", order)
            startActivity(intent)
            Log.d("APPOINTMENT", "$it")
        }

    }

}
