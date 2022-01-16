package org.tuwaiq.carwash.model

import java.io.Serializable

data class Day(
    val _id: String?= null,
    val day: String,
    val slot: Slot
) : Serializable