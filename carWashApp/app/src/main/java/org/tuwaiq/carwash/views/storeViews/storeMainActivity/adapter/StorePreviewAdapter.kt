package org.tuwaiq.carwash.views.storeViews.storeMainActivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.util.HelperFunctions

class StorePreviewAdapter :
    RecyclerView.Adapter<StorePreviewHolder>() {
    private var data: List<ServiceModel>

    init {
        data = listOf()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorePreviewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.services_row, parent, false)
        return StorePreviewHolder(v)
    }

    override fun onBindViewHolder(holder: StorePreviewHolder, position: Int) {
        val service = data[position]
        holder.apply {
            imgServiceLogo.setImageBitmap(service.logo?.let {
                HelperFunctions.decodePicFromApi(it) })
            tvServiceTitle.text = service.title
            tvServicePrice.text = service.price.toString()
            tvServiceDuration.text = service.durationInMin.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(list: List<ServiceModel>) {
        data = list
        notifyDataSetChanged()
    }

}

class StorePreviewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val imgServiceLogo = v.findViewById<ImageView>(R.id.imageViewRowServiceLogo)
    val tvServiceTitle = v.findViewById<TextView>(R.id.textViewRowServiceTitle)
    val tvServicePrice = v.findViewById<TextView>(R.id.textViewRowServicePrice)
    val tvServiceDuration = v.findViewById<TextView>(R.id.textViewServicesRowDuration)
}