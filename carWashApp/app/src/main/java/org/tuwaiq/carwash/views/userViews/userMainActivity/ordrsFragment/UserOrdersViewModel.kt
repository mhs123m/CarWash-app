package org.tuwaiq.carwash.views.userViews.userMainActivity.ordrsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tuwaiq.carwash.model.Orders
import org.tuwaiq.carwash.repository.AppointmentRepository
import org.tuwaiq.carwash.util.HelperFunctions

class UserOrdersViewModel: ViewModel() {
    private val appointmentRepository = AppointmentRepository()
    val ordersLiveData = MutableLiveData<List<Orders>>()

    fun getUserOrders(userId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.getUserOrders(userId)
            HelperFunctions.ifResponseNotNullPostValue(ordersLiveData,response)
        }
    }
}