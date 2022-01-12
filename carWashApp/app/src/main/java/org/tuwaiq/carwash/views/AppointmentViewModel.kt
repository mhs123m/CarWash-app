package org.tuwaiq.carwash.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.repository.AppointmentRepository
import org.tuwaiq.carwash.util.HelperFunctions

class AppointmentViewModel : ViewModel() {

    private val appointmentRepository = AppointmentRepository()
    val newAppointmentLiveData = MutableLiveData<Appointment>()

    // new appointment
    fun newAppointment(appointment: Appointment) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.newAppointment(appointment)
            HelperFunctions.ifResponseNotNullPostValue(newAppointmentLiveData, response)
        }
    }
}