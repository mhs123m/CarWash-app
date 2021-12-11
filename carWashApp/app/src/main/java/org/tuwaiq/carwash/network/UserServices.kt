package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServices {

    @POST("users/register")
    fun registerNewUser(@Body user: User): Call<User>
}