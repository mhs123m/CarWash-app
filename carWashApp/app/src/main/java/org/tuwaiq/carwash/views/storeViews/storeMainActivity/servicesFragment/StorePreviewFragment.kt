package org.tuwaiq.carwash.views.storeViews.storeMainActivity.servicesFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.ServiceViewModel
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.servicesFragment.serviceActivities.AddServiceActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.StorePreviewAdapter

class StorePreviewFragment : Fragment() {

    lateinit var viewModel: ServiceViewModel
    lateinit var adapter: StorePreviewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_preview, container, false)

        // call viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as StoreMainActivity
            )[ServiceViewModel::class.java]

        // link recyclerView
        val storePrevRecyclerView = v.findViewById<RecyclerView>(R.id.storePreviewRecyclerView)
        storePrevRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

//         initialize adapter
        adapter = StorePreviewAdapter()
        // get   store Id
        val storeId = Globals.sharedPreferences.getString("ID", null)
        viewModel.getAllServicesOfStore(storeId!!)

        viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->

            adapter.setData(serviceList)
            storePrevRecyclerView.adapter = adapter
        }

        //link fab btn -> on click, open add activity
        v.findViewById<ExtendedFloatingActionButton>(R.id.FABaddService).setOnClickListener {
            startActivity(Intent(v.context, AddServiceActivity::class.java))
        }

        return v
    }


    companion object {
        @JvmStatic
        fun newInstance() = StorePreviewFragment()
    }


}