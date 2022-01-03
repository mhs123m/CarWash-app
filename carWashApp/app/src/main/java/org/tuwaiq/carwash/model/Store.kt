package org.tuwaiq.carwash.model

import java.io.Serializable

data class Store(
    val _id: String?,
    val name: String,
    val email: String,
    val phone: String,
    val password: String?,
    val logo: String?,
    val geometry: Geometry?,

    ) : Serializable