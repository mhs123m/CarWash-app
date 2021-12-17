package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import retrofit2.Call
import retrofit2.http.*

interface StoreServices {

    // Register store
    @POST("stores/register")
    fun registerNewStore(@Body store: Store): Call<Store>

    // LogIn store
    @POST("stores/login")
    fun storeLogIn(@Body loginModel: LoginModel): Call<Store>

    // get all stores
    @GET("stores")
    fun getAllStores(): Call<List<Store>>

    // update store info
    @PATCH("stores/{storeId}")
    fun updateStoreInfo (@Path("storeId") id:String, @Body store: Store ): Call<Store>
}