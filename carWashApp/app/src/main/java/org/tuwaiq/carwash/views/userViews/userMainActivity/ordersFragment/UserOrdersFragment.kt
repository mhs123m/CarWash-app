package org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserOrdersFragment : Fragment() {
    lateinit var viewModel: UserOrdersViewModel
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

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // link recycler
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.userOrdersRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)

        //get userId
        val userId = Globals.sharedPreferences.getString("ID", null)
        viewModel.getUserOrders(userId!!)
        viewModel.ordersLiveData.observe(this) { ordersList ->
            val sorted = ordersList.sortedWith(
                compareBy(
                    { it.day.slot.status.compareTo(SlotStatus.Pending) },
                    { it.day.slot.status.compareTo(SlotStatus.Cancelled) },
                    { it.day.slot.status.compareTo(SlotStatus.Done) },
                )
            )
            mRecyclerView.adapter = UserOrdersAdapter(sorted)
        }


    }
}