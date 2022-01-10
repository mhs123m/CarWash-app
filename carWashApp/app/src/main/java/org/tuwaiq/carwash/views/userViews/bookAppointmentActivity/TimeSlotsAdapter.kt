package org.tuwaiq.carwash.views.userViews.bookAppointmentActivity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.TimeSlot
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class TimeSlotsAdapter(private val clickListener: (time: String, index: Int) -> Unit) :
    RecyclerView.Adapter<TimeSlotsHolder>() {
    private var data: List<TimeSlot>
    private lateinit var chipList: ArrayList<Chip>

    init {
        data = listOf()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotsHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.time_slots_row, parent, false)
        return TimeSlotsHolder(v, clickListener)
    }

    override fun onBindViewHolder(holder: TimeSlotsHolder, position: Int) {
        holder.timeSlotChip.text = TimeSlotsHelperFunctions.convertIndexToTime(position)

        chipList = arrayListOf(holder.timeSlotChip)

        if (!chipList.contains(holder.timeSlotChip)) chipList.add(holder.timeSlotChip)



        holder.timeSlotChip.setOnClickListener {
            clickListener.invoke(holder.timeSlotChip.text.toString(), position)
        }


    }

    override fun getItemCount() = 20 // num of time slots from 9 am to 7 pm of 30 minutes intervals


}


class TimeSlotsHolder(v: View, private val clickListener: (time: String, index: Int) -> Unit) :
    RecyclerView.ViewHolder(v) {
    val timeSlotChip: Chip = v.findViewById(R.id.timeSlotChip)

}