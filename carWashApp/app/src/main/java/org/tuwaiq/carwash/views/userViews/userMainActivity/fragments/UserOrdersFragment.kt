package org.tuwaiq.carwash.views.userViews.userMainActivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.AppointmentViewModel
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserOrdersFragment : Fragment() {
    lateinit var viewModel: AppointmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_orders, container, false)

        // call viewModel
        viewModel = ViewModelProvider(
            requireActivity() as UserMainActivity
        )[AppointmentViewModel::class.java]

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}