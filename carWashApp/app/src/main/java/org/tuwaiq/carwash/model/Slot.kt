package org.tuwaiq.carwash.model

import org.tuwaiq.carwash.model.enums.SlotStatus

data class Slot(
    val _id: String? = null,
    val available: Boolean? = false,
    val duration: Int = 30,
    val index: Int,
    val status: SlotStatus
)



