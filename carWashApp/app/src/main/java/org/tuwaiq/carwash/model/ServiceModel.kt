package org.tuwaiq.carwash.model

data class ServiceModel(

    val _id: String?,
    val logo: String?,
    val title: String,
    val durationInMin: Double,
    val description: String,
    val available: Boolean = false,
    val price: Double,
    val createdAt: String,
    val updatedAt: String,
    val storeId: String,
)