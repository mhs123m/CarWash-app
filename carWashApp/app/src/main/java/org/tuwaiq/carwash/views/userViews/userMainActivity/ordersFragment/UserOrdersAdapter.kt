package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.util.TimeSlotsHelperFunctions

class UserOrdersAdapter(var data: List<Order> = mutableListOf()) :
    RecyclerView.Adapter<UserOrdersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserOrdersHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_order_row, parent, false)
        return UserOrdersHolder(v)
    }

    override fun onBindViewHolder(holder: UserOrdersHolder, position: Int) {
        val order = data[position]
        val context = holder.itemView.context
        val storeAddress = order.storeId.geometry?.formattedAddress
        val status = order.day.slot.status
        val time = TimeSlotsHelperFunctions.convertIndexToTime(order.day.slot.index)

        holder.apply {

//            order.serviceId?.let {
//                serviceTitle.text = order.serviceId.title
//                servicePrice.text = order.serviceId.price.toString()
//            }
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
                            .getColor(itemView.context, R.color.primaryGreen)
                    )
                    imgStatusIcon.setImageResource(R.drawable.done_icon)

                }
                SlotStatus.Cancelled -> {
                    appointmentStatus.text = SlotStatus.Cancelled.toString()
                    constraintLayout.setBackgroundColor(
                        ContextCompat
                            .getColor(itemView.context, R.color.primaryRed)
                    )
                    imgStatusIcon.setImageResource(R.drawable.cancelled_icon)

                }
                else -> {
                    // pending is set by default (hard coded)
                }
            }

        }

        holder.tvReschedule.setOnClickListener {
            if (holder.appointmentStatus.text.toString() == SlotStatus.Pending.toString()) {
                // inflate bottom sheet and cancel option is disabled
                val i = Intent(context, RescheduleAppointmentActivity::class.java)
                i.putExtra("order", order)
                context.startActivity(i)
            }
        }
        // start navigation to store
        holder.imgNavigation.setOnClickListener {
            val lat = order.storeId.geometry?.coordinates?.get(1).toString()
            val lng = order.storeId.geometry?.coordinates?.get(0).toString()

            val gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            context.startActivity(mapIntent)
        }

        holder.tvCancelled.setOnClickListener {

        }


    }

    override fun getItemCount(): Int = data.size
}

class UserOrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val storeName = itemView.findViewById<TextView>(R.id.textViewStoreNameRow)
    val tvReschedule = itemView.findViewById<TextView>(R.id.textViewRescheduleRow)
    val tvCancelled = itemView.findViewById<TextView>(R.id.textViewCancelRow)
    val appointmentTime = itemView.findViewById<TextView>(R.id.textViewTimeRow)
    val imgNavigation = itemView.findViewById<ImageView>(R.id.imageViewNavigationRow)
    val storeLocation = itemView.findViewById<TextView>(R.id.textViewStoreLocationRow)
    val appointmentStatus = itemView.findViewById<TextView>(R.id.textViewStatusRow)
    val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraintBackGroundRow)
    val imgStatusIcon = itemView.findViewById<ImageView>(R.id.imageViewStatusIconRow)
}