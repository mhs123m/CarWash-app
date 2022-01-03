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
    val editedServiceLiveData = MutableLiveData<ServiceModel>()
    val deletedServiceLiveData = MutableLiveData<ServiceModel>()

    fun getAllServicesOfStore(storeId: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.getAllServicesOfStore(storeId)
            HelperFunctions.ifResponseNotNullPostValue(allServicesOfStoreLiveData, response)
        }
    }

    fun addNewService(xAuthHeader: String, serviceModel: ServiceModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.addNewService(xAuthHeader, serviceModel)
            HelperFunctions.ifResponseNotNullPostValue(newServiceLiveData, response)
        }
    }

    fun editService(xAuthHeader: String, serviceId: String, serviceModel: ServiceModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.editService(xAuthHeader, serviceId, serviceModel)
            HelperFunctions.ifResponseNotNullPostValue(editedServiceLiveData, response)
        }
    }

    fun deleteService(xAuthHeader: String, serviceId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = serviceRepository.deleteService(xAuthHeader, serviceId)
            HelperFunctions.ifResponseNotNullPostValue(deletedServiceLiveData, response)
        }
    }
}