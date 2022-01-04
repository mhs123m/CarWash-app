package org.tuwaiq.carwash.views.userViews.bookAppointmentActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.TimeSlots
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class TimeSlotsAdapter : RecyclerView.Adapter<TimeSlotsHolder>() {
    private var data: List<TimeSlots>

    init {
        data = listOf()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotsHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.time_slots_row, parent, false)
        return TimeSlotsHolder(v)
    }

    override fun onBindViewHolder(holder: TimeSlotsHolder, position: Int) {
        holder.timeSlotChip.text = TimeSlotsHelperFunctions.convertIndexToTime(position)
        for (slotValue in data) {
        val slot: Int = slotValue.time.toString().toInt()
            if (slot == position) { // if slot is booked
                holder.timeSlotChip.apply {
                    isChecked = true
                    isCheckable = false
                }

            }
        }
    }

    override fun getItemCount() = 20 // num of time slots from 9 am to 7 pm of 30 minutes intervals


}

class TimeSlotsHolder(v: View) : RecyclerView.ViewHolder(v) {
    val timeSlotChip: Chip = v.findViewById(R.id.timeSlotChip)
}