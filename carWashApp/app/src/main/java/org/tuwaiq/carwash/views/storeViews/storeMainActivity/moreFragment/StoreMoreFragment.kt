package org.tuwaiq.carwash.views.storeViews.storeMainActivity.moreFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.MoreModel

class StoreMoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_more, container, false)

        //link views
//        val testBtnToSetting = v.findViewById<Button>(R.id.buttonTakeMeToSettings)
//        testBtnToSetting.setOnClickListener {
//            startActivity(Intent(v.context, StoreInfoActivity::class.java))
//        }
//
//        val btnLogOut = v.findViewById<Button>(R.id.buttonStoreLogOut)
//        btnLogOut.setOnClickListener {
//            HelperFunctions.signOut(v.context)
////            val i = Intent(v.context, UserSignInActivity::class.java)
////            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
////                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
////                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
////            startActivity(i)
//        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = view.findViewById<RecyclerView>(R.id.moreStoreRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)

        val moreModel = listOf(
            MoreModel(R.drawable.more_store_icon, getString(R.string.profile)),
            MoreModel(R.drawable.more_share_icon, getString(R.string.shareApp)),
            MoreModel(R.drawable.more_send_feedback_icon, getString(R.string.feedBack)),
            MoreModel(R.drawable.more_dark_mode_icon, getString(R.string.darkMode)),
            MoreModel(R.drawable.more_language_icon, getString(R.string.language)),
            MoreModel(R.drawable.more_log_out_icon, getString(R.string.logOut)),
        )

            mRecyclerView.adapter = StoreMoreAdapter(moreModel)
    }

    companion object {
        @JvmStatic
        fun newInstance() = StoreMoreFragment()
    }
}