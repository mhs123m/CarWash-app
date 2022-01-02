package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.ServiceModel
import retrofit2.Response
import retrofit2.http.*

interface ServiceServices {

    // let's use coroutines here instead of callback just to show off

    // get all services of a store
    @GET("/stores/{storeId}/services")
    suspend fun getAllServicesOfStore(
        @Header("x-auth") xAuthHeader: String,
        @Path("storeId") storeId: String
    ): Response<List<ServiceModel>>

    // POST new service by store
    // /services/new
    @POST("/services/new")
    suspend fun addNewService(
        @Header("x-auth") xAuthHeader: String,
        @Body serviceModel: ServiceModel
    ): Response<ServiceModel>
}