package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserHomeFragment : Fragment() {
    lateinit var userHomeAdapter: UserHomeAdapter
    lateinit var viewModel: StoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_home, container, false)

        // call viewModel
        viewModel = ViewModelProvider(
            requireActivity() as UserMainActivity
        )[StoreViewModel::class.java]

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //link recyclerview
        val userHomeRecyclerView = view.findViewById<RecyclerView>(R.id.userHomeRecyclerView)
        userHomeRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        userHomeAdapter = UserHomeAdapter(activity!!)

        val cpb = view.findViewById<CircularProgressBar>(R.id.circularProgressBar2)
        cpb.visibility = View.VISIBLE
        // get data of stores and set to adapter
        viewModel.getAllStores()
        viewModel.allStoresLiveData.observe(this, {
            userHomeAdapter.setData(it)
            userHomeRecyclerView.adapter = userHomeAdapter
            cpb.visibility = View.GONE
        })

    }
}