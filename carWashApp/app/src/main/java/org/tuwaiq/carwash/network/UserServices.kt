package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserServices {

    // Register user
    @POST("users/register")
    @Headers("Content-Type: application/json") // to see content of the error
    fun registerNewUser(@Body user: User): Call<User>

    // LogIn user
    @POST("users/login")
    fun userLogIn(@Body loginModel: LoginModel): Call<User>

    // get one user
    @GET("users/{userId}")
    fun getUserById (@Path("userId") userId: String): Call<User>

    // update one user
    @PATCH("users/{userId}")
    fun updateUser(@Path("userId") userId: String, @Body user: User): Call<User>
}