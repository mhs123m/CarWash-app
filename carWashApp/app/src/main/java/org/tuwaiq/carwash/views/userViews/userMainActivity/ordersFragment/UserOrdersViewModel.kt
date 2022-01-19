package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.repository.AppointmentRepository
import org.tuwaiq.carwash.utils.HelperFunctions

class UserOrdersViewModel: ViewModel() {
    private val appointmentRepository = AppointmentRepository()
    val ordersLiveData = MutableLiveData<List<Order>>()
    val updatedAppointment = MutableLiveData<Appointment>()
    val deletedAppointment = MutableLiveData<Appointment>()

    // get userOrders
    fun getUserOrders(userId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.getUserOrders(userId)
            HelperFunctions.ifResponseNotNullPostValue(ordersLiveData,response)
        }
    }

    // patch appointment (reschedule or cancel)
    fun updateAppointment(appointmentId: String, appointment: Appointment){
        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.updateAppointment(appointmentId, appointment)
            HelperFunctions.ifResponseNotNullPostValue(updatedAppointment,response)
        }
    }

    // delete appointment
    fun cancelAppointment(appointmentId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.deleteAppointment(appointmentId)
            HelperFunctions.ifResponseNotNullPostValue(deletedAppointment,response)
        }
    }
}