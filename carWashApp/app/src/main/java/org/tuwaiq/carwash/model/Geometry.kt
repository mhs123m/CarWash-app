package org.tuwaiq.carwash.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type


data class Geometry(
    val coordinates: List<Double>? = null,
    val formattedAddress: String? = null,
    val type: String = "Point"
) : Serializable