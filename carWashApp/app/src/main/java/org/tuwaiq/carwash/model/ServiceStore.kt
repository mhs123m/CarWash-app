package org.tuwaiq.carwash.model

import java.io.Serializable

data class ServiceStore(

    val _id: String?,
    val logo: String?,
    val title: String,
    val description: String?,
    val price: Double,
    val durationInMin: Double?,
    val available: Boolean? = false,
    val createdAt: String?,
    val updatedAt: String?,
    val storeId: Store?,
) : Serializable {

//    constructor(
//        _id: String,
//        price: Double,
//        title: String,
//    ) : this(
//        _id,
//        null,
//        title,
//        null,
//        price,
//        null,
//        null,
//        null,
//        null,
//        null
//    )


}