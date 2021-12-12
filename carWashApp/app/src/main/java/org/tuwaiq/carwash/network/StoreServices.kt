package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.Store
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface StoreServices {

    @POST("stores/register")
    fun registerNewStore(@Body store: Store): Call<Store>
}