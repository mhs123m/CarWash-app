package org.tuwaiq.carwash.views.userViews.userMainActivity.ordrsFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Orders
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class UserOrdersAdapter(var data: List<Orders> = mutableListOf()) :
    RecyclerView.Adapter<UserOrdersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserOrdersHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_order_row, parent, false)
        return UserOrdersHolder(v)
    }

    override fun onBindViewHolder(holder: UserOrdersHolder, position: Int) {
        val order = data[position]
        val storeAddress = order.storeId.geometry?.formattedAddress
        val status = order.day.slot.status
        val time = TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)

        holder.apply {

            order.serviceId?.let {
                serviceTitle.text = order.serviceId.title
                servicePrice.text = order.serviceId.price.toString()
            }
            order.storeId?.let {
                storeName.text = order.storeId.name
                storeLocation.text = storeAddress
            }
            appointmentTime.text = time

            when (status) {
                SlotStatus.Done -> {
                    appointmentStatus.text = SlotStatus.Done.toString()
                    constraintLayout.setBackgroundColor(
                        ContextCompat
                            .getColor(itemView.context, R.color.constraintBackgroundDone)
                    )
                    imgStatusIcon.setImageResource(R.drawable.done_icon)

                }
                SlotStatus.Cancelled -> {
                    appointmentStatus.text = SlotStatus.Cancelled.toString()
                    constraintLayout.setBackgroundColor(
                        ContextCompat
                            .getColor(itemView.context, R.color.constraintBackgroundCancelled)
                    )
                    imgStatusIcon.setImageResource(R.drawable.cancelled_icon)

                }
                else -> {
                    // pending is set by default (hard coded)
                }
            }

        }


    }

    override fun getItemCount(): Int = data.size
}

class UserOrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val storeName = itemView.findViewById<TextView>(R.id.textViewStoreNameRow)
    val serviceTitle = itemView.findViewById<TextView>(R.id.textViewServiceNameRow)
    val appointmentTime = itemView.findViewById<TextView>(R.id.textViewTimeRow)
    val servicePrice = itemView.findViewById<TextView>(R.id.textViewPriceRow)
    val storeLocation = itemView.findViewById<TextView>(R.id.textViewStoreLocationRow)
    val appointmentStatus = itemView.findViewById<TextView>(R.id.textViewStatusRow)
    val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraintBackGroundRow)
    val imgStatusIcon = itemView.findViewById<ImageView>(R.id.imageViewStatusIconRow)
}