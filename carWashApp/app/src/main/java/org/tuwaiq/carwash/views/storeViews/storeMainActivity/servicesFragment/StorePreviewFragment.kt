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
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.ServiceStore
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.ServiceViewModel
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.servicesFragment.serviceActivities.AddServiceActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.StorePreviewAdapter
import org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.stepOnePickService.DisplayAdapter

class StorePreviewFragment : Fragment() {

    private lateinit var viewModel: ServiceViewModel
    private lateinit var adapter: StorePreviewAdapter
    private val displayedList = mutableListOf<ServiceStore>()
    private lateinit var cpb: CircularProgressBar
    private lateinit var storePrevRecyclerView: RecyclerView
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
        storePrevRecyclerView = v.findViewById<RecyclerView>(R.id.storePreviewRecyclerView)
        storePrevRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//         initialize adapter
        adapter = StorePreviewAdapter(displayedList)
        storePrevRecyclerView.adapter = adapter
        cpb = v.findViewById(R.id.circularProgressBarPreview)


        //link fab btn -> on click, open add activity
        v.findViewById<ExtendedFloatingActionButton>(R.id.FABaddService).setOnClickListener {
            startActivity(Intent(v.context, AddServiceActivity::class.java))
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewData()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StorePreviewFragment()
    }

    private fun setRecyclerViewData() {
        cpb.visibility = View.VISIBLE
        // get   store Id
        val storeId = Globals.sharedPreferences.getString("ID", null)
        viewModel.getAllServicesOfStore(storeId!!)

        viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->

            displayedList.clear()
            displayedList.addAll(serviceList)
            adapter.notifyDataSetChanged()
            cpb.visibility = View.GONE

        }
    }


}