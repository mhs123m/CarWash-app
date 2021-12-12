package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserServices {

    @POST("users/register")
    @Headers("Content-Type: application/json") // to see content of the error
    fun registerNewUser(@Body user: User): Call<User>
}