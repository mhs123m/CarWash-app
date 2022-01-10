package org.tuwaiq.carwash.views.userViews.userMainActivity.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.userViews.displayServices.DisplayServicesActivity

class UserHomeAdapter() : RecyclerView.Adapter<UserHomeHolder>() {
    private var data: List<Store>

    init {
        data = listOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHomeHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.stores_list_item_for_users_row, parent, false)
        return UserHomeHolder(v)
    }

    override fun onBindViewHolder(holder: UserHomeHolder, position: Int) {
        val store = data[position]
        holder.apply {
            imgViewStoreLogo.setImageBitmap(store.logo?.let {
                HelperFunctions.decodePicFromApi(it)
            })
            tvStoreName.text = store.name
            //tvStoreDistanceInKm.text =  (store.location - currntUser.location(get it from sharePref?) TODO
//            tvStoreRatingAverage.text = store.ratingAve TODO
//            tvStoreRatingNumberOfRating.text = store.numberOfRating TODO
        }
        holder.itemView.setOnClickListener {
            val i = Intent(it.context, DisplayServicesActivity::class.java)
            i.putExtra("store", store)
            it.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(list: List<Store>) {
        data = list
        notifyDataSetChanged()
    }
}

class UserHomeHolder(v: View) : RecyclerView.ViewHolder(v) {
    val imgViewRatingIcon = v.findViewById<ImageView>(R.id.imageViewRowRatingIcon)
    val imgViewStoreLogo = v.findViewById<ImageView>(R.id.imageViewRowStoreLogo)
    val tvStoreName = v.findViewById<TextView>(R.id.textViewRowStoreName)
    val tvStoreDistanceInKm = v.findViewById<TextView>(R.id.textViewRowDistanceKM)
    val tvStoreRatingAverage = v.findViewById<TextView>(R.id.textViewRowRatingAverage)
    val tvStoreRatingNumberOfRating = v.findViewById<TextView>(R.id.textViewRowNumberOfRating)
}