package org.tuwaiq.carwash.repository

import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Orders
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.AppointmentServices
import retrofit2.Response


class AppointmentRepository {
    private val appointmentServices = Api.getInstance().create(AppointmentServices::class.java)

    // post new appointment
    suspend fun newAppointment(appointment: Appointment): Response<Appointment> {

        return appointmentServices.newAppointmentByUser(appointment)
    }

    // get all orders of a users (appointment)
    suspend fun getUserOrders(userId: String) : Response<List<Orders>>{
        return appointmentServices.getUserOrders(userId)
    }
}