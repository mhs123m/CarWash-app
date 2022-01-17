package org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.fragments.storeAppointments.upcomingPast.FragmentAdapter

class StoreHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewPager2 = view.findViewById<ViewPager2>(R.id.mViewPager2)
        var mTabLayout = view.findViewById<TabLayout>(R.id.mTabLayout)

        var titles = arrayOf("Upcoming", "Past")
        var iconsofTabs = arrayOf(R.drawable.calendar_grey_icon,R.drawable.past_icon)
        viewPager2.adapter = FragmentAdapter(activity!!)


        TabLayoutMediator(mTabLayout, viewPager2) { tab, position ->
            tab.text = titles[position]
            tab.setIcon(iconsofTabs[position])
        }.attach()
    }
    companion object {
        @JvmStatic
        fun newInstance() = StoreHomeFragment()
    }
}