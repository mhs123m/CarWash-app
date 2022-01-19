package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepTwoPickTime

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.databinding.ActivityBookAppointmentBinding
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.AppointmentViewModel
import android.content.Intent
import androidx.appcompat.content.res.AppCompatResources
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.*
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepThreeConfirm.ConfirmAppointmentActivity


class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var service: ServiceStore
    private lateinit var binding: ActivityBookAppointmentBinding
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


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = intent.getSerializableExtra("serviceToBook") as ServiceStore

        // link views
        val btnPrevious = binding.buttonAppPrevious
        btnConfirm = binding.buttonAppConfirm
        calendarView = binding.calendarView
        tvSelectedTime = binding.textViewSelectedTime
        tvSelectedTimeResult = binding.textViewTimeSelectedResult
        timeSlotRecyclerView = binding.timeSlotsrecyclerView
        timeSlotRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, GridLayoutManager.VERTICAL)


        loadTimeSlots()
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            date = "$year-${month+1}-$dayOfMonth"
        }

        btnPrevious.setOnClickListener { finish() }

        btnConfirm.setOnClickListener {
            getAppointmentInfo()

            val i = Intent(this, ConfirmAppointmentActivity::class.java)
            i.putExtra("serviceToBook",service)
            i.putExtra("appointment",appointment)
            startActivity(i)
             // fill appointment info
//            bookAppointment(appointment) TODO
        }


    }

    private fun loadTimeSlots() {


        timeSlotRecyclerView.adapter = TimeSlotsAdapter { time: String, index: Int ->
            tvSelectedTimeResult.text = time
            indexOfAppointment = index
            println(indexOfAppointment.toString())
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getAppointmentInfo() {
        val userId = Globals.sharedPreferences.getString("ID", null)

        slot = Slot(null, false, 30, indexOfAppointment!!, SlotStatus.Pending)
        if (!this::date.isInitialized) {
            val d = Calendar.getInstance()
            val y = d.get(Calendar.YEAR)
            val m = d.get(Calendar.MONTH)
            val day = d.get(Calendar.DAY_OF_MONTH)
            date = "$y-${m+1}-$day"
        }
        day = Day(null, date, slot)

        appointment = Appointment(
            null,
            day,
            service._id!!,
            service.storeId!!._id!!,
            userId!!,
            null,
            null
        )
    }

}