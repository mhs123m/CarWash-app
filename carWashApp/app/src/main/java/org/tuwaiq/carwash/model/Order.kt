package org.tuwaiq.carwash.model

import java.io.Serializable

data class Order(
    val _id: String,
    val day: Day,
    val serviceId: ServiceModel,
    val storeId: Store,
    val userId: User,
    val createdAt: String,
    val updatedAt: String?,
) : Serializable