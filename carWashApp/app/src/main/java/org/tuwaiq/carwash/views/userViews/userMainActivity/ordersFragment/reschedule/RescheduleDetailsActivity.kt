package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.reschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.mikhaellopez.circularprogressbar.CircularProgressBar
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
    private lateinit var cpb: CircularProgressBar
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
        cpb = binding.circularProgressBarConfirm
        appointment = Appointment(
            order._id,
            order.day,
            order.serviceId._id!!,
            order.storeId._id!!,
            order.userId._id!!,
            null,
            null
        )
    }


    private fun setViewsWithData() {
        cpb.visibility = View.VISIBLE
        val day = order.day.day
        val time = TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)
        order.storeId?.let {
            tvStoreName.text = it.name
        }
        tvDateAndTime.text =
            "$day | $time"
        order?.let {
            tvServiceTitle.text = it.serviceId.title
            tvServicePrice.text = it.serviceId.price.toString()
            cpb.visibility = View.GONE
        }
    }

    private fun rescheduleAppointment(appointment: Appointment) {
        cpb.visibility = View.VISIBLE
        viewModel.updateAppointment(order._id, appointment)
        viewModel.updatedAppointment.observe(this) {
            val intent = Intent(this, AppointmentRescheduledActivity::class.java)
            intent.putExtra("order", order)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            startActivity(intent)
            cpb.visibility = View.GONE
            Log.d("APPOINTMENT", "$it")
        }

    }

}
