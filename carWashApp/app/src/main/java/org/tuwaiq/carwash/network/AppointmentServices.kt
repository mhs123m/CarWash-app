package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Order
import retrofit2.Response
import retrofit2.http.*

interface AppointmentServices {

    // post a new appointment
    @POST("/appointments/new")
    suspend fun newAppointmentByUser(@Body appointment: Appointment): Response<Appointment>

    // USER APPOINTMENTS ROUTES

    // get all appointments of a user (previous, current orders of a user)
    @GET("/users/appointments/{userId}")
    suspend fun getUserOrders(
        @Path("userId") userId: String,
    ): Response<List<Order>>

    // patch an appointment (reschedule or cancel)
    @PATCH("/appointments/{appointmentId}")
    suspend fun updateOrder(
        @Path("appointmentId") appointmentId: String,
        @Body appointment: Appointment
    ) : Response<Appointment>

    // delete appointment (cancel)
    @DELETE("/appointments/{appointmentId}")
    suspend fun deleteOrder(
        @Path("appointmentId") appointmentId: String
    ) : Response<Appointment>

    // STORE APPOINTMENTS ROUTES

    // get all appointments of a store (previous, current orders of a user)
    @GET("/appointments/{storeId}")
    suspend fun getStoreOrders(
        @Path("storeId") storeId: String,
    ): Response<List<Order>>
}