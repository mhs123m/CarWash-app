package org.tuwaiq.carwash.views.userMainActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.storeLoginAndRegister.StoreLogInViewModel
import org.tuwaiq.carwash.views.userMainActivity.adapter.UserHomeAdapter

class UserHomeFragment : Fragment() {
    lateinit var userHomeAdapter: UserHomeAdapter
    lateinit var viewModel: StoreLogInViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_home, container, false)

        // call viewModel
        viewModel = ViewModelProvider(
            requireActivity() as UserMainActivity
        )[StoreLogInViewModel::class.java]



        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //link recyclerview
        val userHomeRecyclerView = view.findViewById<RecyclerView>(R.id.userHomeRecyclerView)
        userHomeRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        userHomeAdapter = UserHomeAdapter()

        // get data of stores and set to adapter
        viewModel.getAllStores()
        viewModel.allStoresLiveData.observe(this,{
            userHomeAdapter.setData(it)
            userHomeRecyclerView.adapter = userHomeAdapter
        })

    }
}