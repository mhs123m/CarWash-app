package org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeHomeFragment.upcomingPast

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
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeHomeFragment.PastAdapter


class PastFragment : Fragment() {
    private lateinit var viewModel: StoreOrderViewModel
    private lateinit var mRecyclerView: RecyclerView
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
        setRecyclerViewWithData()
    }

    private fun setRecyclerViewWithData(){
        val storeId = Globals.sharedPreferences.getString("ID",null)
        viewModel.getStoreOrders(storeId!!)
        viewModel.storeOrdersLiveData.observe(this){ ordersList ->
            val pastList =  ordersList.filter {
                it.day.slot.status != SlotStatus.Pending
            }
            mRecyclerView.adapter = PastAdapter(pastList)
        }
    }

}