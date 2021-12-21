package org.tuwaiq.carwash.model

data class Store(
    val _id: String?,
    val name: String,
    val email: String,
    val phone: String,
    val password: String?,
    val logo: String?,
    val location: Geometry?
)