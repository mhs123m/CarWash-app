package org.tuwaiq.carwash.views.userViews.bookAppointmentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.tuwaiq.carwash.databinding.ActivityBookAppointmentBinding
import org.tuwaiq.carwash.model.ServiceModel

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var service: ServiceModel
    private lateinit var binding: ActivityBookAppointmentBinding
    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedTime: TextView
    private lateinit var tvSelectedTimeResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = intent.getSerializableExtra("serviceToBook") as ServiceModel

        // link views
        calendarView = binding.calendarView
        tvSelectedTime = binding.textViewSelectedTime
        tvSelectedTimeResult = binding.textViewTimeSelectedResult



        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->


        }


    }


}