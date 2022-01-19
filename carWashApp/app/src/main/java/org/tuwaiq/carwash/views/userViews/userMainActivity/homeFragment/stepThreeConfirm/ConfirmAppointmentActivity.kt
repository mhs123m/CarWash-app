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
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.ServiceStore
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.AppointmentViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.step4AppointmentBooked.AppointmentBookedActivity

class ConfirmAppointmentActivity : AppCompatActivity() {
    private val viewModel: AppointmentViewModel by viewModels()
    private lateinit var binding: ActivityConfirmAppointmentBinding
    private lateinit var service: ServiceStore
    private lateinit var appointment: Appointment
    private lateinit var tvStoreName: TextView
    private lateinit var tvDateAndTime: TextView
    private lateinit var tvServiceTitle: TextView
    private lateinit var tvServicePrice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appointment = intent.getSerializableExtra("appointment") as Appointment
        service = intent.getSerializableExtra("serviceToBook") as ServiceStore
//        val userId = Globals.sharedPreferences.getString("ID",null)


        // link views
        initializeViews()
        setViewsWithData()

        binding.textViewBack.setOnClickListener {
            finish()
            println("back")
        }

        binding.textViewConfirm.setOnClickListener {
            bookAppointment(appointment)
            println("confirm")
        }
    }

    private fun initializeViews() {
        tvStoreName = binding.textViewStoreNameConfirm
        tvDateAndTime = binding.textViewDateTimeConfirm
        tvServiceTitle = binding.textViewServiceTitleConfirm
        tvServicePrice = binding.textViewServicePriceConfirm
    }


    private fun setViewsWithData() {
        val day = appointment.day.day
        val time = TimeSlotsHelperFunctions.convertIndexToTime(appointment.day.slot.index)
        service.storeId?.let {
            tvStoreName.text = it.name
        }
        tvDateAndTime.text =
            "$day | $time"
        service?.let {
            tvServiceTitle.text = it.title
            tvServicePrice.text = it.price.toString()
        }
    }

    private fun bookAppointment(appointment: Appointment) {
        viewModel.newAppointment(appointment)
        viewModel.newAppointmentLiveData.observe(this) {
            val intent = Intent(this, AppointmentBookedActivity::class.java)
            intent.putExtra("serviceToBook", service)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
            Log.d("APPOINTMENT", "$it")
        }

    }
}