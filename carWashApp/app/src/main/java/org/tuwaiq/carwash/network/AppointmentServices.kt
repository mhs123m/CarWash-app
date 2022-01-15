package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Orders
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppointmentServices {

    // post a new appointment
    @POST("/appointments/new")
    suspend fun newAppointmentByUser(@Body appointment: Appointment): Response<Appointment>

    // get all appointments of a user (previous, current orders of a user)
    @GET("/users/appointments/{userId}")
    suspend fun getUserOrders(
        @Path("userId") userId: String,
        ): Response<List<Orders>>

}