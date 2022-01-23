package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepOnePickService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Order
import org.tuwaiq.carwash.model.ServiceModel
import org.tuwaiq.carwash.model.ServiceStore
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.views.ServiceViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.ordersFragment.UserOrdersAdapter

class DisplayServicesActivity : AppCompatActivity() {
    lateinit var store: Store
    private lateinit var adapter: DisplayAdapter
    private val displayedList = mutableListOf<ServiceStore>()
    private lateinit var cpb: CircularProgressBar
    private lateinit var displayRecyclerView: RecyclerView
    val viewModel: ServiceViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_services)

        store = intent.getSerializableExtra("store") as Store

        // backBtn
        findViewById<ImageButton>(R.id.imageButtonBB).setOnClickListener { finish() }
        // link recyclerView
        displayRecyclerView = findViewById(R.id.DisplayRecyclerView)
        displayRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        cpb = findViewById(R.id.circularProgressBarDisplayService)
        adapter = DisplayAdapter(displayedList)
        displayRecyclerView.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewData()
    }

   private fun setRecyclerViewData(){
       cpb.visibility = View.VISIBLE
       viewModel.getAllServicesOfStore(store._id!!)

       viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->

           displayedList.clear()
           displayedList.addAll(serviceList)
           adapter.notifyDataSetChanged()
           cpb.visibility = View.GONE
       }
   }
}