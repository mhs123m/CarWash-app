package org.tuwaiq.carwash.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.repository.ServiceRepository
import org.tuwaiq.carwash.util.HelperFunctions

class ServiceViewModel : ViewModel() {

    private val serviceRepository = ServiceRepository()
    val allServicesOfStoreLiveData = MutableLiveData<List<ServiceModel>>()
    val newServiceLiveData = MutableLiveData<ServiceModel>()

    fun getAllServicesOfStore(xAuthHeader: String, storeId: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.getAllServicesOfStore(xAuthHeader, storeId)
            HelperFunctions.ifResponseNotNullPostValue(allServicesOfStoreLiveData,response)
        }
    }

    fun addNewService (xAuthHeader: String, serviceModel: ServiceModel){
        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.addNewService(xAuthHeader, serviceModel)
            HelperFunctions.ifResponseNotNullPostValue(newServiceLiveData,response)
        }
    }
}