package org.tuwaiq.carwash.model

data class Geometry(
    val type: String = "Point",
    val coordinates: Coordinates,
    val formattedAddress: String,
)