package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.enums.SlotStatus
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity

class UpcomingFragment : Fragment() {
    private lateinit var viewModel: StoreOrderViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: UpcomingAdapter
    private val displayedList = mutableListOf<Order>()
    private lateinit var cpb: CircularProgressBar
    private lateinit var imgEmptyState: ImageView
    private lateinit var tvEmptyState: TextView
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
        return inflater.inflate(R.layout.fragment_upcomming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.upcomingStoreRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = UpcomingAdapter(displayedList)
        mRecyclerView.adapter = adapter
        cpb = view.findViewById(R.id.circularProgressBarUpcoming)
        imgEmptyState = view.findViewById(R.id.imageViewEmpUpcoming)
        tvEmptyState = view.findViewById(R.id.textViewEmpUpcoming)

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
            val upcomingList =  ordersList.filter {
                it.day.slot.status == SlotStatus.Pending
            }
            displayedList.clear()
            displayedList.addAll(upcomingList)
            adapter.notifyDataSetChanged()
            cpb.visibility = View.GONE

            HelperFunctions.checkEmptyState(
                imgEmptyState,
                tvEmptyState,
                mRecyclerView,
                displayedList
            )
        }
    }

}
