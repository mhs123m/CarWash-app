package org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.tuwaiq.carwash.databinding.ActivityStorePastOrderBinding
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class StorePastOrderActivity : AppCompatActivity() {
    private lateinit var order: Order
    private lateinit var binding: ActivityStorePastOrderBinding
    private lateinit var tvUserName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorePastOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order = intent.getSerializableExtra("order") as Order

        // link views
        initializeViews()
        setViewsWithData()


        binding.buttonBackDetails.setOnClickListener {
            finish()
        }
        binding.imageViewBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun initializeViews() {
        tvUserName = binding.textViewPastStoreNameDetails
        tvDateAndTime = binding.textViewPastStoreDateTime
        tvServiceTitle = binding.textViewPastServiceTitleDetails
        tvServicePrice = binding.textViewPastStoreServicePriceDetails
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