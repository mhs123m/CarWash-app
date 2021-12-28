package org.tuwaiq.carwash.views.storeViews.storeMainActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.views.ServiceViewModel
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.adapter.StorePreviewAdapter
import org.tuwaiq.carwash.views.userViews.UserViewModel
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity

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

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize adapter
        adapter = StorePreviewAdapter()
        // link recyclerView
        val storePrevRecyclerView = view.findViewById<RecyclerView>(R.id.storePreviewRecyclerView)
        storePrevRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

        // get token and store Id
        val xAuthHeader = Globals.sharedPreferences.getString("Token", null)
        val storeId = Globals.sharedPreferences.getString("ID", null)
        viewModel.getAllServicesOfStore(xAuthHeader!!, storeId!!)

        viewModel.allServicesOfStoreLiveData.observe(this) { serviceList ->
                adapter.setData(serviceList)
                storePrevRecyclerView.adapter = adapter
        }


    }


}