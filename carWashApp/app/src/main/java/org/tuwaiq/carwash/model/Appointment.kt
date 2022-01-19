package org.tuwaiq.carwash.model

import java.io.Serializable

data class Appointment(
    val _id: String? = null,
    val day: Day,
    val serviceId: String,
    val storeId: String,
    val userId: String,
    val createdAt: String?,
    val updatedAt: String?,
) : Serializable