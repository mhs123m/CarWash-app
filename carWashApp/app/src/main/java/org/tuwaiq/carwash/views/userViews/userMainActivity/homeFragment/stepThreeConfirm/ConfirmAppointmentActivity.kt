package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepThreeConfirm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityConfirmAppointmentBinding
import org.tuwaiq.carwash.databinding.ActivityUserProfileBinding
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.AppointmentViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.step4AppointmentBooked.AppointmentBookedActivity

class ConfirmAppointmentActivity : AppCompatActivity() {
    private val viewModel: AppointmentViewModel by viewModels()
    private lateinit var binding: ActivityConfirmAppointmentBinding
    private lateinit var order: Order
    private lateinit var appointment: Appointment
    private lateinit var tvStoreName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmAppointmentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_confirm_appointment)

        order = intent.getSerializableExtra("appointment") as Order
        appointment = Appointment(
            order._id,
            order.day,
            order.serviceId._id!!,
            order.storeId._id!!,
            order.userId._id!!,
            null,
            null
        )

        // link views
        initializeViews()
        setViewsWithData()

        binding.textViewBack.setOnClickListener {
            finish()
        }

        binding.textViewConfirm.setOnClickListener {
            bookAppointment(appointment)
        }
    }

    private fun initializeViews() {
        tvStoreName = binding.textViewStoreNameConfirm
        tvDateAndTime = binding.textViewDateTimeConfirm
        tvServiceTitle = binding.textViewServiceTitleConfirm
        tvServicePrice = binding.textViewServicePriceConfirm
    }


    private fun setViewsWithData() {
        val day = order.day.day
        val time = TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)
        order.storeId?.let {
            tvStoreName.text = it.name
        }
        tvDateAndTime.text =
            "$day | $time"
        order.serviceId?.let {
            tvServiceTitle.text = it.title
            tvServicePrice.text = it.price.toString()
        }
    }

    private fun bookAppointment(appointment: Appointment) {
        viewModel.newAppointment(appointment)
        viewModel.newAppointmentLiveData.observe(this) {
            val intent = Intent(this, AppointmentBookedActivity::class.java)
            intent.putExtra("order", order)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
            Log.d("APPOINTMENT", "$it")
        }

    }
}