package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast.PastOrderDetailsActivity

class PastAdapter(var data: List<Order> = mutableListOf()) : RecyclerView.Adapter<PastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.store_past_order_row, parent, false)
        return PastHolder(v)
    }

    override fun onBindViewHolder(holder: PastHolder, position: Int) {
        val order = data[position]
        val context = holder.itemView.context
        val status = order.day.slot.status
        val name: String? = order.userId.fullname
        val t: String? = TimeSlotsHelperFunctions.getStartTime(order.day.slot.index)
        val title: String? = order.serviceId.title
        holder.apply {
            name?.let { userName.text = it }
            t?.let { serviceTime.text = it }
            title?.let { serviceTitle.text = it }

            when (status) {
                SlotStatus.Done -> {
                    appointmentStatus.text = context.getString(R.string.done)
                    constraintLayout.setBackgroundColor(
                        ContextCompat
                            .getColor(itemView.context, R.color.primaryGreen)
                    )
                    imgStatusIcon.setImageResource(R.drawable.done_icon)

                }
                else -> {
                    appointmentStatus.text = context.getString(R.string.cancelled)
                    constraintLayout.setBackgroundColor(
                        ContextCompat
                            .getColor(itemView.context, R.color.primaryRed)
                    )
                    imgStatusIcon.setImageResource(R.drawable.cancelled_icon)

                }

            }
        }

        holder.itemView.setOnClickListener {
            val i = Intent(context, PastOrderDetailsActivity::class.java)
            i.putExtra("order", order)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int = data.size
}

class PastHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName = itemView.findViewById<TextView>(R.id.textViewPastUserNameRow)
    val serviceTime = itemView.findViewById<TextView>(R.id.textViewPastTimeRow)
    val serviceTitle = itemView.findViewById<TextView>(R.id.textViewPastServiceTitleRow)
    val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.pastConstraintLayout)
    val appointmentStatus = itemView.findViewById<TextView>(R.id.textViewStatusPast)
    val imgStatusIcon = itemView.findViewById<ImageView>(R.id.imageViewStatusPast)

}