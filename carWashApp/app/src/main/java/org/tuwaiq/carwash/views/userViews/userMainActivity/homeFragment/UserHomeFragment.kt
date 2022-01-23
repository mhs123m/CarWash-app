package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserHomeFragment : Fragment() {
    lateinit var userHomeAdapter: UserHomeAdapter
    private var displayedList = mutableListOf<Store>()
    lateinit var viewModel: StoreViewModel
    private lateinit var userHomeRecyclerView: RecyclerView
    private lateinit var imgEmptyState: ImageView
    private lateinit var tvEmptyState: TextView
    private lateinit var cpb: CircularProgressBar
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

        linkViews(view)

    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewData()
    }

    private fun linkViews(view: View) {
        //link recyclerview
        userHomeRecyclerView = view.findViewById<RecyclerView>(R.id.userHomeRecyclerView)
        userHomeRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        userHomeAdapter = UserHomeAdapter(activity!!, displayedList)
        userHomeRecyclerView.adapter = userHomeAdapter

        cpb = view.findViewById(R.id.circularProgressBar2)
        imgEmptyState = view.findViewById(R.id.imageViewEmpUserHome)
        tvEmptyState = view.findViewById(R.id.textViewEmpUserHome)

    }

    private fun setRecyclerViewData() {
        cpb.visibility = View.VISIBLE
        // get data of stores and set to adapter
        viewModel.getAllStores()
        viewModel.allStoresLiveData.observe(this, {

            displayedList.clear()
            displayedList.addAll(it)
            userHomeAdapter.notifyDataSetChanged()
            cpb.visibility = View.GONE


            HelperFunctions.checkEmptyState(
                imgEmptyState,
                tvEmptyState,
                userHomeRecyclerView,
                displayedList
            )


        })
    }
}