package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserServices {

    // Register user
    @POST("users/register")
    @Headers("Content-Type: application/json") // to see content of the error
    fun registerNewUser(@Body user: User): Call<User>

    // LogIn user
    @POST("users/login")
    fun userLogIn(@Body loginModel: LoginModel): Call<User>
}