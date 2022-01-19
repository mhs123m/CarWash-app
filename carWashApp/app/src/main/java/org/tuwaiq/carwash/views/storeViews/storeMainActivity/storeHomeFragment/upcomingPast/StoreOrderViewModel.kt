package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.repository.AppointmentRepository
import org.tuwaiq.carwash.utils.HelperFunctions

class StoreOrderViewModel: ViewModel() {
    private val appointmentRepository = AppointmentRepository()
    val storeOrdersLiveData = MutableLiveData<List<Order>>()

    // get storeOrders
    fun getStoreOrders(storeId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = appointmentRepository.getStoreOrders(storeId)
            HelperFunctions.ifResponseNotNullPostValue(storeOrdersLiveData,response)
        }
    }
}