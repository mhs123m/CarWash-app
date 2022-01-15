package org.tuwaiq.carwash.model

data class Orders(
    val _id: String,
    val day: Day,
    val serviceId: ServiceModel,
    val storeId: Store,
    val userId: User,
    val createdAt: String,
    val updatedAt: String,
)