package org.tuwaiq.carwash.views.userViews.bookAppointmentActivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.databinding.ActivityBookAppointmentBinding
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Day
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.Slot
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.views.AppointmentViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookAppointmentActivity : AppCompatActivity() {
    private val viewModel: AppointmentViewModel by viewModels()
    private lateinit var service: ServiceModel
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = intent.getSerializableExtra("serviceToBook") as ServiceModel

        // link views
        val btnPrevious = binding.buttonAppPrevious
        val btnConfirm = binding.buttonAppConfirm
        calendarView = binding.calendarView
        tvSelectedTime = binding.textViewSelectedTime
        tvSelectedTimeResult = binding.textViewTimeSelectedResult
        timeSlotRecyclerView = binding.timeSlotsrecyclerView
        timeSlotRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, GridLayoutManager.VERTICAL)




        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            loadTimeSlots()
            date = "$year-$month-$dayOfMonth"
//            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//            val date = LocalDate.parse(d, firstApiFormat)
//            Log.d("date testing", "${date.dayOfMonth}")




        }

        btnPrevious.setOnClickListener {
            finish()
        }
        btnConfirm.setOnClickListener {
            val userId = Globals.sharedPreferences.getString("ID", null)
            appointment = Appointment(
                null,
                day,
                service._id!!,
                service.storeId,
                userId!!,
                null,
                null
            )

            bookAppointment(appointment)
        }


    }

    private fun loadTimeSlots() {


        timeSlotRecyclerView.adapter = TimeSlotsAdapter { time: String, index: Int ->
            tvSelectedTimeResult.text = time
            indexOfAppointment = index
            slot = Slot(null, false, 30, indexOfAppointment!!, SlotStatus.PENDING)
            day = Day(null, date.toString(), slot)

        }
    }

    private fun bookAppointment(appointment: Appointment) {
        viewModel.newAppointment(appointment)
        viewModel.newAppointmentLiveData.observe(this) {
            Log.d("APPOINTMENT", "$it")
        }

    }


}