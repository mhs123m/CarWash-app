package org.tuwaiq.carwash.views.userViews.userMainActivity.fragments

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.util.LocationHelperFunctions
import org.tuwaiq.carwash.views.userViews.displayServices.DisplayServicesActivity
import java.math.RoundingMode
import java.text.DecimalFormat

class UserHomeAdapter(var activity: Activity) : RecyclerView.Adapter<UserHomeHolder>() {
    private lateinit var locationHelperFunctions: LocationHelperFunctions
    private var data: List<Store>

    init {
        data = listOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHomeHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.stores_list_item_for_users_row, parent, false)
        locationHelperFunctions = LocationHelperFunctions.getInstance()
        return UserHomeHolder(v)
    }

    override fun onBindViewHolder(holder: UserHomeHolder, position: Int) {
        val store = data[position]
        val userLoc = getLastKnownLocation(activity)
        var distanceAway: Double? = null
        if (store.geometry?.coordinates?.size == 2) {
            distanceAway = distanceBtwTwoPoints(
                userLoc.latitude, userLoc.longitude,
                store.geometry.coordinates[1],
                store.geometry.coordinates[0],
            )
        }
        holder.apply {
            imgViewStoreLogo.setImageBitmap(store.logo?.let {
                HelperFunctions.decodePicFromApi(it)
            })
            tvStoreName.text = store.name
            tvStoreDistanceInKm.text =  distanceAway.toString()
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
    private fun getLastKnownLocation(activity: Activity): LatLng {
        val lastKnownLocation = locationHelperFunctions.getLastKnownLocation(activity)
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }

    private fun distanceBtwTwoPoints(
        startLat: Double,
        startLng: Double,
        endLat: Double,
        endLng: Double
    ): Double {
        val result = FloatArray(1)
        Location.distanceBetween(startLat, startLng, endLat, endLng, result)
        val distance = result[0] / 1000
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(distance).toDouble()
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