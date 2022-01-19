package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.tuwaiq.carwash.databinding.ActivityAppointmentDetailsBinding
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions

class AppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentDetailsBinding
    private lateinit var order: Order
    private lateinit var tvStoreName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order = intent.getSerializableExtra("order") as Order

        // link views
        initializeViews()
        setViewsWithData()

        binding.textViewReschedule.setOnClickListener {
            val i = Intent(this,RescheduleAppointmentActivity::class.java)
            i.putExtra("order", order)
            startActivity(i)
        }

        binding.textViewCancel.setOnClickListener {
            val i = Intent(this,CancelAppointmentActivity::class.java)
            i.putExtra("order", order)
            startActivity(i)
        }

        binding.buttonBackDetails.setOnClickListener {
            finish()
        }
        binding.imageViewBackBtn.setOnClickListener {
            finish()
        }

    }

    private fun initializeViews() {
        tvStoreName = binding.textViewStoreNameDetails
        tvDateAndTime = binding.textViewDateTime
        tvServiceTitle = binding.textViewServiceTitleDetails
        tvServicePrice = binding.textViewServicePriceDetails
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


}