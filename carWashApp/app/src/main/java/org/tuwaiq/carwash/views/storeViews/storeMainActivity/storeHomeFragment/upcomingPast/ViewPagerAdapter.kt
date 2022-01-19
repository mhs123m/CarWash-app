package org.tuwaiq.carwash.views.storeViews.storeMainActivity.storeHomeFragment.upcomingPast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(Activity:FragmentActivity):FragmentStateAdapter(Activity) {
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
