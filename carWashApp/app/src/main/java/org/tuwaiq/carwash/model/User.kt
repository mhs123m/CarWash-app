package org.tuwaiq.carwash.model

import java.io.Serializable

data class User(
    val _id: String?,
    val email: String,
    val fullname: String,
    val phone: String,
    val password: String?,
): Serializable {

}