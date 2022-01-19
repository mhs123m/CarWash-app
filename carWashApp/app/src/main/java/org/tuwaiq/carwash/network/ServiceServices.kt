package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.ServiceStore
import retrofit2.Response
import retrofit2.http.*

interface ServiceServices {

    // let's use coroutines here instead of callback just to show off

    // get all services of a store
    @GET("/stores/{storeId}/services")
    suspend fun getAllServicesOfStore(
        @Path("storeId") storeId: String
    ): Response<List<ServiceStore>>

    // POST new service by store
    // /services/new
    @POST("/services/new")
    suspend fun addNewService(
        @Header("x-auth") xAuthHeader: String,
        @Body serviceModel: ServiceModel
    ): Response<ServiceModel>

    //PATCH
    // /services/:serviceId
    @PATCH("/services/{serviceId}")
    suspend fun editService(
        @Header("x-auth") xAuthHeader: String,
        @Path("serviceId") serviceId: String,
        @Body serviceModel: ServiceModel
    ): Response<ServiceModel>

    // DELETE
    // // /services/:serviceId
    @DELETE("/services/{serviceId}")
    suspend fun deleteService(
        @Header("x-auth") xAuthHeader: String,
        @Path("serviceId") serviceId: String
    ): Response<ServiceModel>
}