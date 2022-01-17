package org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeHomeFragment.upcomingPast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.tuwaiq.carwash.databinding.ActivityUpcomingOrderDetailsBinding
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class UpcomingOrderDetailsActivity : AppCompatActivity() {
    private lateinit var order: Order
    private lateinit var binding: ActivityUpcomingOrderDetailsBinding
    private lateinit var tvUserName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
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
            // TODO update status from Pending to Done
        }

        binding.textViewUnattended.setOnClickListener {
            // TODO update status from pending to cancelled, or delete it
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
}