package org.tuwaiq.carwash.repository

import org.tuwaiq.carwash.model.Appointment
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.AppointmentServices
import retrofit2.Response


class AppointmentRepository {
    private val appointmentServices = Api.getInstance().create(AppointmentServices::class.java)


    // USER REPO

    // post new appointment
    suspend fun newAppointment(appointment: Appointment): Response<Appointment> {

        return appointmentServices.newAppointmentByUser(appointment)
    }

    // get all orders of a users (appointment)
    suspend fun getUserOrders(userId: String): Response<List<Order>> {
        return appointmentServices.getUserOrders(userId)
    }

    // patch appointment (reschedule or cancel)
    suspend fun updateAppointment(
        appointmentId: String,
        appointment: Appointment
    ): Response<Appointment> {
        return appointmentServices.updateOrder(appointmentId, appointment)
    }

    // delete appointment
    suspend fun deleteAppointment(appointmentId: String) : Response<Appointment> {
        return appointmentServices.deleteOrder(appointmentId)
    }

    // STORE REPO

    // get all appointments of a store (previous, current orders of a user)
    suspend fun getStoreOrders(storeId: String): Response<List<Order>> {
        return appointmentServices.getStoreOrders(storeId)
    }
}