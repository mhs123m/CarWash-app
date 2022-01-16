package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.displayServices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.views.ServiceViewModel

class DisplayServicesActivity : AppCompatActivity() {
    lateinit var store: Store
    val viewModel: ServiceViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_services)

        store = intent.getSerializableExtra("store") as Store

        // link recyclerView
        val displayRecyclerView = findViewById<RecyclerView>(R.id.DisplayrecyclerView)
        displayRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)


        viewModel.getAllServicesOfStore(store._id!!)

        viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->

            // initialize adapter
            val adapter = DisplayAdapter(serviceList)
            displayRecyclerView.adapter = adapter
        }


    }
}