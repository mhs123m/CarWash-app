package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.utils.TimeSlotsHelperFunctions

class UpcomingAdapter (var data: List<Order> = mutableListOf()) : RecyclerView.Adapter<UpcomingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.store_upcoming_order_row, parent, false)
        return UpcomingHolder(v)
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        val order = data[position]
        val context = holder.itemView.context
        val name = order.userId.fullname
        val t = TimeSlotsHelperFunctions.getStartTime(order.day.slot.index)
        val title = order.serviceId.title
        holder.apply {
            name?.let { userName.text = it }
            t?.let { serviceTime.text = it }
            title?.let { serviceTitle.text = it }
        }

        holder.itemView.setOnClickListener {
            val i = Intent(context, UpcomingOrderDetailsActivity::class.java)
            i.putExtra("order", order)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int = data.size
}

class UpcomingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName = itemView.findViewById<TextView>(R.id.textViewUserNameRow)
    val serviceTime = itemView.findViewById<TextView>(R.id.textViewUpcomingTimeRow)
    val serviceTitle = itemView.findViewById<TextView>(R.id.textViewUpcomingServiceTitleRow)
}