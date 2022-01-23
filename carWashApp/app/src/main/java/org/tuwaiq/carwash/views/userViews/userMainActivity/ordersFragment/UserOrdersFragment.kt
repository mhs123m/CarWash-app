package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserOrdersFragment : Fragment() {
    private lateinit var viewModel: UserOrdersViewModel
    private lateinit var adapter: UserOrdersAdapter
    private val displayedList = mutableListOf<Order>()
    private lateinit var cpb: CircularProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_orders, container, false)

        // call viewModel
        viewModel = ViewModelProvider(
            requireActivity() as UserMainActivity
        )[UserOrdersViewModel::class.java]

        adapter = UserOrdersAdapter(displayedList)
        // link recycler
        val mRecyclerView = v.findViewById<RecyclerView>(R.id.userOrdersRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(v.context)
        mRecyclerView.adapter = adapter
        cpb = v.findViewById(R.id.circularProgressBar3)
        return v
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        //get userId

        cpb.visibility = View.VISIBLE
        val userId = Globals.sharedPreferences.getString("ID", null)
        viewModel.getUserOrders(userId!!)
        viewModel.ordersLiveData.observe(this) { ordersList ->
            val sorted = ordersList.sortedWith(
                compareBy(
                    { it.day.slot.status.compareTo(SlotStatus.Pending) },
                    { it.day.slot.status.compareTo(SlotStatus.Done) },
                    { it.day.slot.status.compareTo(SlotStatus.Cancelled) },
                )
            )
            displayedList.clear()
            displayedList.addAll(sorted)
            adapter.notifyDataSetChanged()
            cpb.visibility = View.GONE
        }

    }
}