package org.tuwaiq.carwash.repository

import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.ServiceStore
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.ServiceServices
import retrofit2.Response

class ServiceRepository {

    private val serviceServices = Api.getInstance().create(ServiceServices::class.java)

    // get all services of one store
    suspend fun getAllServicesOfStore(
        storeId: String
    ): Response<List<ServiceStore>> {

        return serviceServices.getAllServicesOfStore(storeId)
    }

    // post new service
    suspend fun addNewService(
        xAuthHeader: String,
        serviceModel: ServiceModel
    ): Response<ServiceModel> {

        return serviceServices.addNewService(xAuthHeader, serviceModel)
    }

    // edit service
    suspend fun editService(
        xAuthHeader: String,
        serviceId: String,
        serviceModel: ServiceModel
    ): Response<ServiceModel> {

        return serviceServices.editService(xAuthHeader, serviceId, serviceModel)
    }

    // delete one
    suspend fun deleteService(
        xAuthHeader: String,
        serviceId: String,
    ): Response<ServiceModel> {

        return serviceServices.deleteService(xAuthHeader, serviceId)
    }
}