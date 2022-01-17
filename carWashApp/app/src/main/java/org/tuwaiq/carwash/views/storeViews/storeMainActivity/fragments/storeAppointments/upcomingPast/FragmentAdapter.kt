package org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments.upcomingPast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments.PastFragment
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments.UpcomingFragment

class FragmentAdapter(Activity:FragmentActivity):FragmentStateAdapter(Activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return UpcomingFragment()
            1 -> return PastFragment()
        }
        return UpcomingFragment()
    }
}
