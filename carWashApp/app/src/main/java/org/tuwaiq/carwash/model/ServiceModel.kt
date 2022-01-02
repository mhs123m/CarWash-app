package org.tuwaiq.carwash.model

data class ServiceModel(

    val _id: String?,
    val logo: String?,
    val title: String,
    val description: String?,
    val price: Double,
    val durationInMin: Double,
    val available: Boolean = false,
    val createdAt: String?,
    val updatedAt: String?,
    val storeId: String,
){

}