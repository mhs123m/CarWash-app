package org.tuwaiq.carwash.views.userViews.displayServices

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ServicesRowBinding
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.editServiceActivity.EditServiceActivity
import org.tuwaiq.carwash.views.userViews.BookAppointmentActivity

class DisplayAdapter(private var data: List<ServiceModel>) : RecyclerView.Adapter<DisplayHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.services_row, parent, false)
        return DisplayHolder(v)
    }

    override fun onBindViewHolder(holder: DisplayHolder, position: Int) {
        val service = data[position]
        holder.apply {
            imgServiceLogo?.setImageBitmap(service.logo?.let {
                HelperFunctions.decodePicFromApi(it) })
            tvServiceTitle?.text = service.title
            tvServicePrice?.text = service.price.toString()
            tvServiceDuration?.text = service.durationInMin.toString()
        }

        holder.itemView.setOnClickListener {
            val i = Intent(it.context, BookAppointmentActivity::class.java)
            i.putExtra("serviceToBook", service)
            it.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int = data.size

}

class DisplayHolder(v: View) : RecyclerView.ViewHolder(v) {
    val imgServiceLogo: ImageView? = v.findViewById(R.id.imageViewRowServiceLogo)
    val tvServiceTitle: TextView? = v.findViewById(R.id.textViewRowServiceTitle)
    val tvServicePrice: TextView? = v.findViewById(R.id.textViewRowServicePrice)
    val tvServiceDuration: TextView? = v.findViewById(R.id.textViewServicesRowDuration)
}