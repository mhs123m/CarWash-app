package org.tuwaiq.carwash.repository

import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.ServiceServices
import retrofit2.Response

class ServiceRepository {

    private val serviceServices = Api.getInstance().create(ServiceServices::class.java)

    // get all services of one store
    suspend fun getAllServicesOfStore(
        xAuthHeader: String,
        storeId: String
    ): Response<List<ServiceModel>> {

        return serviceServices.getAllServicesOfStore(xAuthHeader, storeId)
    }

}