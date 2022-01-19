package org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.MoreModel
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.utils.MoreFragmentHelper
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment.storeProfileActivity.StoreProfileActivity

class StoreMoreAdapter(var data: List<MoreModel> = mutableListOf()) :
    RecyclerView.Adapter<MoreHolder>() {
    private val activity : StoreMainActivity = StoreMainActivity()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.more_row_list_items, parent, false)
        return MoreHolder(v)
    }

    override fun onBindViewHolder(holder: MoreHolder, position: Int) {
        val moreModel = data[position]
        val context = holder.itemView.context
        holder.apply {
            textView.text = moreModel.header
            imgView.setImageResource(moreModel.icon)
        }

        holder.itemView.setOnClickListener {
            when(position){
                0 -> {
                        context.startActivity(Intent(context, StoreProfileActivity::class.java))
                }
                1 -> {
                    // share app
                    MoreFragmentHelper.shareApp(context)
                }
                2 -> {
                    // send feed back
                    MoreFragmentHelper.sendFeedBack(context)
                }
                3 -> {
                    // dark mode control
                    MoreFragmentHelper.darkKnightRises(context)
                }
                4 -> {
                    // language control
                    MoreFragmentHelper.changeLanguage(activity, context)
                }
                5 -> {
                    MoreFragmentHelper.signOut(context)
                }

            }
        }
    }

    override fun getItemCount(): Int = data.size

}

class MoreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.textViewMoreRecycler)
    val imgView = itemView.findViewById<ImageView>(R.id.imageViewMoreRecycler)
}