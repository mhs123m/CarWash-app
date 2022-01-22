package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.reschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityRescheduleAppointmentBinding
import org.tuwaiq.carwash.model.*
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepTwoPickTime.TimeSlotsAdapter
import org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.UserOrdersViewModel

class RescheduleAppointmentActivity : AppCompatActivity() {
    private val viewModel: UserOrdersViewModel by viewModels()
    private lateinit var order: Order
    private lateinit var binding: ActivityRescheduleAppointmentBinding
    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedTime: TextView
    private lateinit var tvSelectedTimeResult: TextView
    private lateinit var timeSlotRecyclerView: RecyclerView
    private var indexOfAppointment: Int? = null
    private lateinit var appointment: Appointment
    private lateinit var day: Day
    private lateinit var slot: Slot
    private lateinit var date: String
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRescheduleAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order = intent.getSerializableExtra("order") as Order

        // link views
        val btnPrevious = binding.buttonAppPreviousReschedule
        btnConfirm = binding.buttonAppConfirmReschedule
        calendarView = binding.calendarViewReschedule
        tvSelectedTime = binding.textViewSelectedTimeReschedule
        tvSelectedTimeResult = binding.textViewTimeSelectedResultReschedule
        timeSlotRecyclerView = binding.timeSlotsrecyclerViewReschedule
        timeSlotRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, GridLayoutManager.VERTICAL)
        tvSelectedTimeResult.text =
            TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)


        loadTimeSlots()
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            date = when (month) {
                0 - 8 -> "$year-0${month + 1}-$dayOfMonth"
                else -> "$year-${month + 1}-$dayOfMonth"
            }
        }

        btnPrevious.setOnClickListener { finish() }

        btnConfirm.setOnClickListener {
            getAppointmentInfo() // fill appointment info
        }
    }

    private fun loadTimeSlots() {


        timeSlotRecyclerView.adapter = TimeSlotsAdapter { time: String, index: Int ->
            tvSelectedTimeResult.text = time
            indexOfAppointment = index
            if (indexOfAppointment != null) {
                btnConfirm.isEnabled = true
                btnConfirm.background =
                    AppCompatResources.getDrawable(this, R.drawable.btn_enabled)
                btnConfirm.setTextColor(
                    AppCompatResources.getColorStateList(
                        this,
                        R.color.primaryBackground
                    )
                )
            }

        }
    }



    private fun getAppointmentInfo() {

        slot = Slot(null, false, 30, indexOfAppointment!!, SlotStatus.Pending)
        if (!this::date.isInitialized) {
            date = order.day.day
        }
        day = Day(null, date, slot)

        appointment = Appointment(
            null,
            day,
            order.serviceId._id!!,
            order.storeId._id!!,
            order.userId._id!!,
            null,
            null
        )
    }
}