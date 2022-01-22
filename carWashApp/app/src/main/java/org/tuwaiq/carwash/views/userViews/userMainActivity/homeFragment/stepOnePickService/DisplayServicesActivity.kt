package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepOnePickService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
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

        // backBtn
        findViewById<ImageButton>(R.id.imageButtonBB).setOnClickListener { finish() }
        // link recyclerView
        val displayRecyclerView = findViewById<RecyclerView>(R.id.DisplayRecyclerView)
        displayRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        viewModel.getAllServicesOfStore(store._id!!)

        viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->

            // initialize adapter
            val adapter = DisplayAdapter(serviceList)
            displayRecyclerView.adapter = adapter
        }


    }
}