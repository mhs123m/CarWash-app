package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import org.tuwaiq.carwash.databinding.ActivityUpcomingOrderDetailsBinding
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Day
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.Slot
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.UserOrdersViewModel

class UpcomingOrderDetailsActivity : AppCompatActivity() {
    private lateinit var order: Order
    private lateinit var binding: ActivityUpcomingOrderDetailsBinding
    private lateinit var tvUserName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    val viewModel: UserOrdersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpcomingOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order = intent.getSerializableExtra("order") as Order

        // link views
        initializeViews()
        setViewsWithData()


        binding.imageViewBackBtn.setOnClickListener {
            finish()
        }

        binding.textViewReceived.setOnClickListener {
            isReceived(true)
            finish()
        }

        binding.textViewUnattended.setOnClickListener {
            isReceived(false)
            finish()
        }


    }

    private fun initializeViews() {
        tvUserName = binding.textViewUpcomingStoreNameDetails
        tvDateAndTime = binding.textViewUpcomingStoreDateTime
        tvServiceTitle = binding.textViewStoreUpcomingServiceTitleDetails
        tvServicePrice = binding.textViewStoreUpcomingServicePriceDetails
    }


    private fun setViewsWithData() {
        val day = order.day.day
        val time = TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)
        order.userId?.let { tvUserName.text = it.fullname }
        tvDateAndTime.text = "$day | $time"
        order.serviceId?.let {
            tvServiceTitle.text = it.title
            tvServicePrice.text = it.price.toString()
        }
    }

    private fun isReceived(isReceived: Boolean) {
        val slotTrue = Slot(
            null, null,
            30, order.day.slot.index, SlotStatus.Done
        )
        val slotFalse = Slot(
            null, null,
            30, order.day.slot.index, SlotStatus.Cancelled
        )

        val day = when (isReceived) {
            true -> Day(order.day._id!!, order.day.day, slotTrue)
            else -> Day(order.day._id!!, order.day.day, slotFalse)
        }
        val appointment = Appointment(
            null,
            day,
            order.serviceId._id!!,
            order.storeId._id!!,
            order.userId._id!!,
            null,
            null
        )
        viewModel.updateAppointment(order._id, appointment)
        viewModel.updatedAppointment.observe(this) {
            Log.d("updated?", "yes: $it")
        }
    }
}