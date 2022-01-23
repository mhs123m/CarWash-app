package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

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
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.PastAdapter


class PastFragment : Fragment() {
    private lateinit var viewModel: StoreOrderViewModel
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var adapter: PastAdapter
    private val displayedList = mutableListOf<Order>()
    private lateinit var cpb : CircularProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as StoreMainActivity
            )[StoreOrderViewModel::class.java]
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.pastStoreRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = PastAdapter(displayedList)
        mRecyclerView.adapter = adapter
        cpb = view.findViewById(R.id.circularProgressBarPast)

    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewWithData()
    }

    private fun setRecyclerViewWithData(){
        cpb.visibility = View.VISIBLE
        val storeId = Globals.sharedPreferences.getString("ID",null)
        viewModel.getStoreOrders(storeId!!)
        viewModel.storeOrdersLiveData.observe(this){ ordersList ->
            val pastList =  ordersList.filter {
                it.day.slot.status != SlotStatus.Pending
            }
            displayedList.clear()
            displayedList.addAll(pastList)
            adapter.notifyDataSetChanged()
            cpb.visibility = View.GONE
        }
    }

}