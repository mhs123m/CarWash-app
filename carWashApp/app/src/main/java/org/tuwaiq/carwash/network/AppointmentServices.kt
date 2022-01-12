package org.tuwaiq.carwash.network

import org.tuwaiq.carwash.model.Appointment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AppointmentServices {

    @POST("/appointments/new")
    suspend fun newAppointmentByUser (@Body appointment: Appointment): Response<Appointment>

}